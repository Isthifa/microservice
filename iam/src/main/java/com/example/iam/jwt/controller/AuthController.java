package com.example.iam.jwt.controller;

import com.example.iam.dto.AuthRequestDTO;
import com.example.iam.jwt.services.JwtService;
import com.example.iam.services.UserLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Generate a new token")
    @ApiResponse(responseCode = "200", description = "Token generated successfully")
    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authenticate.isAuthenticated()){
            String token = jwtService.generateToken(authenticate);
            return token;
        }
        else {
            return "Invalid username or password";
        }
}

    @Operation(summary = "Validate a token")
    @ApiResponse(responseCode = "200", description = "Token is valid")
  @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        userLogService.validateToken(token);
        return "Token is valid";
    }

    @Operation(summary = "Extract username from a token")
    @ApiResponse(responseCode = "200", description = "Username extracted successfully")
    @GetMapping("/extractUsername")
    public String extractUsername(@RequestParam("token") String token){
        return jwtService.extractUsername(token);
    }
}
