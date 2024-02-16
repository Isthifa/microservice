package com.example.iam.controller;

import com.example.iam.dto.AuthRequestDTO;
import com.example.iam.dto.UserLogDTO;
import com.example.iam.services.UserLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = {@Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"username\":\"user\",\"password\":\"password\",\"email\":\"user@example.com\",\"role\":\"role\"}"))}),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid UserLogDTO userLogDTO){
        return userLogService.registerUser(userLogDTO);
    }

    @Operation(summary = "Add a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role added successfully",
                    content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"role\":\"role\"}"))}),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Role already exists")
    })
    @PostMapping("/addRole")
    public String AddRole(@RequestParam("Role")String role){
        return userLogService.AddRole(role);
    }

}
