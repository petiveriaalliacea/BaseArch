package com.qakashilliacea.service.impl;

import com.qakashilliacea.config.security.JwtCoder;
import com.qakashilliacea.config.security.PasswordEncoder;
import com.qakashilliacea.entity.EmailVerification;
import com.qakashilliacea.entity.Role;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.EmailVerificationRepository;
import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.service.EmailSenderService;
import com.qakashilliacea.util.ExceptionAnswers;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSenderService emailSenderService;

    @Value("${email.address}")
    private String EMAIL_ADDRESS;

    @Override
    public ResponseDto signUp(RegisterDto dto) {
        ResponseDto responseDto = new ResponseDto<>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            responseDto.setStatus(400);
            responseDto.setErrorMessage(ExceptionAnswers.userWithLoginExists(dto.getUsername()));
            return responseDto;
        }
        dto.setPassword(PasswordEncoder.encode(dto.getPassword()));
        User user = ObjectsMapper.converToUser(dto);
        user.getRoles().add(roleRepository.getById(Role.ROLE_USER));
        user.setIsVerified(false);
        user = userRepository.save(user);
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setVerificationKey(UUID.randomUUID().toString());
        emailVerification.setUserId(user.getId());
        emailVerificationRepository.save(emailVerification);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Verify email address - ");
        simpleMailMessage.setFrom(EMAIL_ADDRESS);
        simpleMailMessage.setText("Your code to verify your email address " + emailVerification.getVerificationKey());
        simpleMailMessage.setTo(user.getUsername());
        emailSenderService.sendEmail(simpleMailMessage);
        responseDto.setSuccess(true);
        responseDto.setData("code have sent to your email address");
        return responseDto;
    }

    @Override
    public ResponseDto signIn(LoginDto dto) {
        ResponseDto response = new ResponseDto<>();
        if (!userRepository.existsByUsername(dto.getUsername())) {
            response.setStatus(404);
            response.setErrorMessage(ExceptionAnswers.userNotFoundByUsername(dto.getUsername()));
            return response;
        }
        User user = userRepository.findByUsername(dto.getUsername());
        if (!PasswordEncoder.verifyPassword(dto.getPassword(), user.getPassword())) {
            response.setStatus(400);
            response.setErrorMessage(ExceptionAnswers.incorrectPassword());
            return response;
        } else {
            response.setSuccess(true);
            response.setData(AuthResponseDto.builder().accessToken(JwtCoder.generateJwt(user)).build());
            return response;
        }
    }

    @Override
    public ResponseDto verifyEmail(String uuid) {
        ResponseDto responseDto = new ResponseDto<>();
        EmailVerification emailVerification = emailVerificationRepository.findEmailVerificationByVerificationKey(uuid);
        if (Objects.nonNull(emailVerification)) {
            User userObj = userRepository.findUserById(emailVerification.getUserId());
            userObj.setIsVerified(true);
            userRepository.save(userObj);
            responseDto.setSuccess(true);
            responseDto.setData("Confirmed");
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(404);
        responseDto.setErrorMessage("Not valid code");
        return responseDto;
    }
}
