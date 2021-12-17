package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {
    private final UserRepository userRepository;
    @Override
    public User getCurrentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElse(userRepository.getById(1L));
    }
}
