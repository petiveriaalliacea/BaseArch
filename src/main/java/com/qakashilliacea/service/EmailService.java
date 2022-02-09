package com.qakashilliacea.service;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.web.dto.ResponseDto;

public interface EmailService {
    void sendEmail(User user, String uuid);

    ResponseDto verifyEmail(String uuid, String code);
}
