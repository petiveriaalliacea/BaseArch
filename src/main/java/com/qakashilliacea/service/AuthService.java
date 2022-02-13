package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.*;

public interface AuthService {
    ResponseDto emailSignUp(UserRegistrationInfoDto dto);

    ResponseDto simpleSignUp (UserRegistrationInfoDto dto);

    ResponseDto signIn(LoginDto dto);
}
