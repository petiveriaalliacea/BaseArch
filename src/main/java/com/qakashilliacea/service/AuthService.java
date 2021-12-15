package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.LoginDto;
import com.qakashilliacea.web.dto.RegisterDto;
import com.qakashilliacea.web.dto.ResponseDto;

public interface AuthService {
    ResponseDto signUp(RegisterDto dto);

    ResponseDto signIn(LoginDto dto);
}