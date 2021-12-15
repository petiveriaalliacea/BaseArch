package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.ResponseDto;
import com.qakashilliacea.web.dto.UserDto;


public interface UserService {
    ResponseDto createUser(UserDto dto);

    ResponseDto updateUser(UserDto dto, Long id);

    ResponseDto readUser(Long id);

    ResponseDto deleteUser(Long id);
}
