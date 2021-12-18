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
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.util.constants.ErrorConstants;
import com.qakashilliacea.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSenderService emailSenderService;

    private static Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public ResponseDto signUp(RegisterDto dto) {
        ResponseDto responseDto = new ResponseDto<>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            responseDto.setStatus(400);
            responseDto.setErrorMessage(ErrorMessages.userWithLoginExists(dto.getUsername()));
            return responseDto;
        }
        dto.setPassword(PasswordEncoder.encode(dto.getPassword()));
        User user = ObjectsMapper.converToUser(dto);
        user.getRoles().add(roleRepository.getById(Role.ROLE_USER));
        user.setIsVerified(false);
        user = userRepository.save(user);
        String uuid = UUID.randomUUID().toString();
        emailSenderService.sendEmail(user, uuid);
        responseDto.setSuccess(true);
        responseDto.setData(uuid);
        return responseDto;
    }

    @Override
    public ResponseDto signIn(LoginDto dto) {
        ResponseDto response = new ResponseDto<>();
        if (!userRepository.existsByUsername(dto.getUsername())) {
            response.setStatus(404);
            response.setErrorMessage(ErrorMessages.userNotFoundByUsername(dto.getUsername()));
            return response;
        }
        User user = userRepository.findByUsername(dto.getUsername()).get();
        if (!PasswordEncoder.verifyPassword(dto.getPassword(), user.getPassword())) {
            response.setStatus(400);
            response.setErrorMessage(ErrorMessages.incorrectPassword());
            return response;
        } else {
            response.setSuccess(true);
            response.setData(AuthResponseDto.builder().accessToken(JwtCoder.generateJwt(user)).build());
            return response;
        }
    }

    @Override
    public ResponseDto verifyEmail(String uuid, String code) {
        ResponseDto responseDto = new ResponseDto<>();
        Optional<EmailVerification> emailVerification = emailVerificationRepository
                .findByCodeAndVerificationKey(code, uuid);
        if (emailVerification.isPresent()) {
            User userObj = emailVerification.get().getUser();
            userObj.setIsVerified(true);
            userRepository.save(userObj);
            emailVerificationRepository.deleteById(emailVerification.get().getId());
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(ErrorConstants.NOT_FOUND);
        responseDto.setErrorMessage("Not found");
        return responseDto;
    }
}
