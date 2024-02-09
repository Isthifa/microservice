package com.example.iam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRoleXref {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userRoleId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private UserLog userLog;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roleId")
    private Role role;

}
