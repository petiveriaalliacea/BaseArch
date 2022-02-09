package com.qakashilliacea.service.impl;

import com.google.common.collect.Sets;
import com.qakashilliacea.config.security.JwtCoder;
import com.qakashilliacea.config.security.PasswordEncoder;
import com.qakashilliacea.entity.Role;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.entity.UserDetailedInfo;
import com.qakashilliacea.respository.EmailVerificationRepository;
import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.respository.UserDetailedInfoRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.service.EmailService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailService emailService;
    private final UserDetailedInfoRepository userDetailedInfoRepository;

    @Override
    public ResponseDto emailSignUp(UserRegistrationInfoDto dto) {
        ResponseDto responseDto = new ResponseDto<>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.userWithLoginExists(dto.getUsername()));
            return responseDto;
        }
        dto.setPassword(PasswordEncoder.encode(dto.getPassword()));
        User user = User.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles(Sets.newHashSet(roleRepository.getById(Role.ROLE_USER)))
                .verified(false).build();
        user = userRepository.save(user);
        UserDetailedInfo userDetailedInfo = ObjectsMapper.convertToUserDetailedInfo(dto);
        userDetailedInfo.setUser(user);
        userDetailedInfoRepository.save(userDetailedInfo);
        String uuid = UUID.randomUUID().toString();
        emailService.sendEmail(user, uuid);
        responseDto.setSuccess(true);
        responseDto.setData(uuid);
        return responseDto;
    }

    @Override
    public ResponseDto simpleSignUp(UserRegistrationInfoDto dto) {
        ResponseDto responseDto = new ResponseDto<>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.userWithLoginExists(dto.getUsername()));
            return responseDto;
        }
        dto.setPassword(PasswordEncoder.encode(dto.getPassword()));
        User user = User.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles(Sets.newHashSet(roleRepository.getById(Role.ROLE_USER)))
                .verified(true).build();
        user = userRepository.save(user);
        UserDetailedInfo userDetailedInfo = ObjectsMapper.convertToUserDetailedInfo(dto);
        userDetailedInfo.setUser(user);
        userDetailedInfoRepository.save(userDetailedInfo);
        responseDto.setSuccess(true);
        return responseDto;
    }

    @Override
    public ResponseDto signIn(LoginDto dto) {
        ResponseDto response = new ResponseDto<>();
        if (!userRepository.existsByUsername(dto.getUsername())) {
            response.setStatus(NOT_FOUND.value());
            response.setErrorMessage(ErrorMessages.userNotFoundByUsername(dto.getUsername()));
            return response;
        }
        User user = userRepository.findByUsername(dto.getUsername()).get();
        if (!PasswordEncoder.verifyPassword(dto.getPassword(), user.getPassword()) && user.getVerified().equals(true)) {
            response.setStatus(BAD_REQUEST.value());
            response.setErrorMessage(ErrorMessages.incorrectPassword());
            return response;
        } else {
            response.setSuccess(true);
            response.setData(AuthResponseDto.builder().accessToken(JwtCoder.generateJwt(user)).build());
            return response;
        }
    }


}
