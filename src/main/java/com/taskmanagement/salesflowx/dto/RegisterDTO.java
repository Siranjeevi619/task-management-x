package com.taskmanagement.salesflowx.dto;


import com.taskmanagement.salesflowx.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    private Role role;
    private String email;
}
