package com.example.iam.services.impl;

import com.example.iam.dto.UserLogDTO;
import com.example.iam.dto.UserRoleDTO;
import com.example.iam.entity.Role;
import com.example.iam.entity.UserLog;
import com.example.iam.entity.UserRoleXref;
import com.example.iam.jwt.services.JwtService;
import com.example.iam.repository.RoleRepository;
import com.example.iam.repository.UserLogRepository;
import com.example.iam.repository.UserRoleXrefRepository;
import com.example.iam.services.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLogImpl implements UserLogService {

    private final UserLogRepository userLogRepository;

    private final RoleRepository roleRepository;

    private final UserRoleXrefRepository userRoleXrefRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserLogImpl(UserLogRepository userLogRepository, RoleRepository roleRepository, UserRoleXrefRepository userRoleXrefRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userLogRepository = userLogRepository;
        this.roleRepository = roleRepository;
        this.userRoleXrefRepository = userRoleXrefRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String registerUser(UserLogDTO userLogDTO) {
        Optional<UserRoleXref> userRoleXref = userRoleXrefRepository.findByUserLog_UsernameAndAndRole_RoleName(
                userLogDTO.getUsername(), Role.RoleType.valueOf(userLogDTO.getRoleName().name()));
       Role.RoleType roles=userLogDTO.getRoleName();
       Optional<Role> rol = roleRepository.findByRoleName(roles);
        if (userRoleXref.isPresent()) {
            return "User already has this role";
        } else if(rol.isPresent()){
            // Retrieve or create the Role entity associated with the given role name

            // Create and save the UserLog entity
            UserLog userLog = UserLog.builder()
                    .username(userLogDTO.getUsername())
                    .password(passwordEncoder.encode(userLogDTO.getPassword()))
                    .email(userLogDTO.getEmail())
                    .build();
            userLogRepository.save(userLog);

            // Create and save the UserRoleXref entity
            UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                    .userLog(userLog)
                    .role(rol.get())
                    .build();
            UserRoleXref userRoleXrefEntity = UserRoleXref.builder()
                    .userLog(userRoleDTO.getUserLog())
                    .role(userRoleDTO.getRole())
                    .build();
            userRoleXrefRepository.save(userRoleXrefEntity);

        }else {
            Role newRole = Role.builder()
                    .roleName(roles)
                    .build();
            roleRepository.save(newRole);
            UserLog userLog = UserLog.builder()
                    .username(userLogDTO.getUsername())
                    .password(passwordEncoder.encode(userLogDTO.getPassword()))
                    .email(userLogDTO.getEmail())
                    .build();
            userLogRepository.save(userLog);
            UserRoleXref userRoleXrefEntity = UserRoleXref.builder()
                    .userLog(userLog)
                    .role(newRole)
                    .build();
            userRoleXrefRepository.save(userRoleXrefEntity);

        }

        return "User registered successfully";
    }


    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    @Override
    public String AddRole(String role) {
        Optional<Role> rol = roleRepository.findByRoleName(Role.RoleType.valueOf(role));
        if (role.isEmpty() || rol.isPresent()) {
            return "Role already exists";
        }
        Role newRole = Role.builder()
                .roleName(Role.RoleType.valueOf(role))
                .build();
        roleRepository.save(newRole);
        return "Role added successfully";
    }
}
