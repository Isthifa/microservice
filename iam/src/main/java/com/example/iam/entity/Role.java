package com.example.iam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    public enum RoleType{ROLE_USER,ROLE_ADMIN}
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private RoleType roleName;
}
