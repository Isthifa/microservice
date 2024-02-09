package com.example.iam.repository;

import com.example.iam.entity.Role;
import com.example.iam.entity.UserRoleXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleXrefRepository extends JpaRepository<UserRoleXref, UUID> {


   Optional<UserRoleXref> findByUserLog_UsernameAndAndRole_RoleName(String username, Role.RoleType roleName);

   List<UserRoleXref> findByUserLogUserId(UUID userId);
}
