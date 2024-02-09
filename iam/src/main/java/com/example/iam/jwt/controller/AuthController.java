package com.example.iam.jwt.controller;

import com.example.iam.dto.AuthRequestDTO;
import com.example.iam.jwt.services.JwtService;
import com.example.iam.services.UserLogService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserLogService userLogService;

    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UserLogService userLogService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userLogService = userLogService;
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authenticate.isAuthenticated()){
            String token = jwtService.generateToken(authenticate);
            return Map.of("token", token).toString();
        }
        else {
            return "Invalid username or password";
        }
}
  @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        userLogService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/extractUsername")
    public String extractUsername(@RequestParam("token") String token){
        return jwtService.extractUsername(token);
    }
}
