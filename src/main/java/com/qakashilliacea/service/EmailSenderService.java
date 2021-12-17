package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.ResponseDto;
import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
    void sendEmail(SimpleMailMessage mailMessage);
}
