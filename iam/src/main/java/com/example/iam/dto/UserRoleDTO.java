package com.example.iam.dto;

import com.example.iam.entity.Role;
import com.example.iam.entity.UserLog;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {

    @Column(nullable = false)
    private UserLog userLog;

    @Column(nullable = false)
    private Role role;
}
