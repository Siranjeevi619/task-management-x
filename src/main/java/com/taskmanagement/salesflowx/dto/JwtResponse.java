package com.taskmanagement.salesflowx.dto;


import com.taskmanagement.salesflowx.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String userId;
    private String userName;
    private Role role;
    private String email;
}
