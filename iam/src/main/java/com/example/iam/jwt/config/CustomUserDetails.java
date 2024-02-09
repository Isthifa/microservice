package com.example.iam.jwt.config;

import com.example.iam.entity.UserLog;
import com.example.iam.entity.UserRoleXref;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {





    private String username;
    private String password;

    List<GrantedAuthority> authorities;

    public CustomUserDetails(UserLog user, List<UserRoleXref> userRoleXrefs) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        authorities = userRoleXrefs.stream().map(userRoleXref -> new SimpleGrantedAuthority(userRoleXref.getRole().getRoleName().toString())).collect(Collectors.toList());
        System.out.printf("authorities: %s\n", authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
