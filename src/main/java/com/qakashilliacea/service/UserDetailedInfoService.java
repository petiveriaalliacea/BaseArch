package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.ResponseDto;
import com.qakashilliacea.web.dto.UserDetailedInfoDto;

public interface UserDetailedInfoService {
    ResponseDto getByUserId(Long userId);

    ResponseDto changeDetails(UserDetailedInfoDto dto);

    ResponseDto getByLoggedUserInfo();
}
