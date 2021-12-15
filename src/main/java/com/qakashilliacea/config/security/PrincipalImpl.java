package com.qakashilliacea.config.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class PrincipalImpl implements Principal {
    @Override
    public String getName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
