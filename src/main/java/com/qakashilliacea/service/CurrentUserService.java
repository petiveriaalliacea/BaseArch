package com.qakashilliacea.service;

import com.qakashilliacea.entity.User;

import java.util.Optional;

public interface CurrentUserService {
    Optional<User> getCurrentUser();
}
