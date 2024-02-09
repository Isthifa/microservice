package com.example.iam.controller;

import com.example.iam.dto.AuthRequestDTO;
import com.example.iam.dto.UserLogDTO;
import com.example.iam.services.UserLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserLogController {

    private final UserLogService userLogService;

    public UserLogController(UserLogService userLogService) {
        this.userLogService = userLogService;
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid UserLogDTO userLogDTO){
        return userLogService.registerUser(userLogDTO);
    }

    @PostMapping("/addRole")
    public String AddRole(@RequestParam("Role")String role){
        return userLogService.AddRole(role);
    }

}
