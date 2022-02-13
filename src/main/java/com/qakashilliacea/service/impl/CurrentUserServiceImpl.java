package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.qakashilliacea.util.constants.Constants.SYSTEM_ID;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Service is used ONLY for Auditing because of its default returning value (SYSTEM user)
 * Some methods that might use this class might be executed by empty principal user
 * That will cause the use of SYSTEM account and logic manipulation with this user which is incorrect
 * In case to work with logged in user use @Autowired Principal in controllers to prevent program from random bugs with SYSTEM user
 */
public class CurrentUserServiceImpl implements CurrentUserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public Optional<User> getCurrentUser() {
        Optional<User> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent()) return user;
        else return userRepository.findById(SYSTEM_ID);
    }
}
