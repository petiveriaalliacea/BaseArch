package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.*;

public interface AuthService {
    ResponseDto signUp(UserRegistrationInfoDto dto);

    ResponseDto signIn(LoginDto dto);

    ResponseDto verifyEmail(String uuid, String code);
}
