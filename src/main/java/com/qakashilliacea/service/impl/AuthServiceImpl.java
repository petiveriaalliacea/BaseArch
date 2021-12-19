package com.qakashilliacea.service.impl;

import com.google.common.collect.Sets;
import com.qakashilliacea.config.security.JwtCoder;
import com.qakashilliacea.config.security.PasswordEncoder;
import com.qakashilliacea.entity.EmailVerification;
import com.qakashilliacea.entity.Role;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.entity.UserDetailedInfo;
import com.qakashilliacea.respository.EmailVerificationRepository;
import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.respository.UserDetailedInfoRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.service.EmailSenderService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.util.constants.ErrorConstants;
import com.qakashilliacea.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSenderService emailSenderService;
    private final UserDetailedInfoRepository userDetailedInfoRepository;

    @Override
    public ResponseDto signUp(UserRegistrationInfoDto dto) {
        ResponseDto responseDto = new ResponseDto<>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            responseDto.setStatus(400);
            responseDto.setErrorMessage(ErrorMessages.userWithLoginExists(dto.getUsername()));
            return responseDto;
        }
        dto.setPassword(PasswordEncoder.encode(dto.getPassword()));
        User user = User.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles(Sets.newHashSet(roleRepository.getById(Role.ROLE_USER)))
                .isVerified(false).build();
        user = userRepository.save(user);
        UserDetailedInfo userDetailedInfo = ObjectsMapper.convertToUserDetailedInfo(dto);
        userDetailedInfo.setUser(user);
        userDetailedInfoRepository.save(userDetailedInfo);
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
