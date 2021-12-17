package com.qakashilliacea.service;

import com.qakashilliacea.entity.EmailVerification;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.web.dto.ResponseDto;
import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
    void sendEmail(User user);
}
