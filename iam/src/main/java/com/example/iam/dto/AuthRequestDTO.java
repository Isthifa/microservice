package com.example.iam.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
}
