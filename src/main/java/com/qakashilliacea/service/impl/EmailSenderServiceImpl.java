package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.EmailVerification;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.EmailVerificationRepository;
import com.qakashilliacea.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final EmailVerificationRepository emailVerificationRepository;

    @Value("${email.address}")
    private String EMAIL_ADDRESS;

    @Override
    public void sendEmail(User user) {
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setVerificationKey(UUID.randomUUID().toString());
        emailVerification.setUserId(user.getId());
        emailVerificationRepository.save(emailVerification);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Verify email address - ");
        simpleMailMessage.setFrom(EMAIL_ADDRESS);
        simpleMailMessage.setText("Your code to verify your email address " + emailVerification.getVerificationKey());
        simpleMailMessage.setTo(user.getUsername());
        javaMailSender.send(simpleMailMessage);
    }
}
