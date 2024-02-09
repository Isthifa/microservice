package com.example.iam.jwt.config;

import com.example.iam.entity.UserLog;
import com.example.iam.entity.UserRoleXref;
import com.example.iam.repository.UserLogRepository;
import com.example.iam.repository.UserRoleXrefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

// custom user details service class
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserLogRepository userLogRepository;

    @Autowired
    private UserRoleXrefRepository userRoleXrefRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLog> user = userLogRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        List<UserRoleXref> userRoleXrefs = userRoleXrefRepository.findByUserLogUserId(user.get().getUserId());
        return new CustomUserDetails(user.get(), userRoleXrefs);
    }
}
