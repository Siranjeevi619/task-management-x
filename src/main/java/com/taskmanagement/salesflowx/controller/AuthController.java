package com.taskmanagement.salesflowx.controller;

import com.taskmanagement.salesflowx.dto.JwtResponse;
import com.taskmanagement.salesflowx.dto.LoginDTO;
import com.taskmanagement.salesflowx.dto.RegisterDTO;
import com.taskmanagement.salesflowx.entity.User;
import com.taskmanagement.salesflowx.repository.AuthRepository;
import com.taskmanagement.salesflowx.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          AuthRepository authRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO newUser) {
        if(authRepository.existsUserByEmail(newUser.getEmail())){
            return "User already exists";
        }
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setRole(newUser.getRole());
        authRepository.save(user);
        return "New user created";
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginDTO loginUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
        );
        User user = authRepository.findByEmail(loginUser.getEmail());
        String token = jwtUtils.generateToken(user);
        return new JwtResponse(token, user.getId(), user.getUsername(), user.getRole(), user.getEmail());
    }
}
