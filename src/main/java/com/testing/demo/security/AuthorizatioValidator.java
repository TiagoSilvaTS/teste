package com.testing.demo.security;

import com.testing.demo.dto.PrincipalDto;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizatioValidator {


    public boolean hasRole(String userRole) {
        return userRole.equals(getPrincipal().getRole().toString());
    }

    public boolean isUser(Long userId) {
        return userId.equals(getPrincipal().getId());
    }

    private PrincipalDto getPrincipal() {
        return (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
