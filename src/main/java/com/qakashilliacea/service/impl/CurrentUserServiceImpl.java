package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrentUserServiceImpl implements CurrentUserService {
    private final UserRepository userRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User getCurrentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }
}
