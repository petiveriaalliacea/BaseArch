package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.entity.UserDetailedInfo;
import com.qakashilliacea.entity.enums.Gender;
import com.qakashilliacea.respository.UserDetailedInfoRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CurrentUserService;
import com.qakashilliacea.service.EmailService;
import com.qakashilliacea.service.UserDetailedInfoService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.ResponseDto;
import com.qakashilliacea.web.dto.UserDetailedInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


import static java.util.Objects.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class UserDetailedInfoImpl implements UserDetailedInfoService {
    private final UserDetailedInfoRepository detailedInfoRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final EmailService emailService;

    @Override
    public ResponseDto getByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findByUser(user.get());
            if (userDetailedInfo.isPresent()) {
                UserDetailedInfoDto userDetailedInfoDto = ObjectsMapper.convertToUserDetailedInfoDto(userDetailedInfo.get());
                user.ifPresent(value -> userDetailedInfoDto.setUsername(value.getUsername()));
                return ResponseDto.builder()
                        .success(true)
                        .data(userDetailedInfoDto).build();
            }
        }
        return ResponseDto.builder()
                .status(NOT_FOUND.value()).build();
    }

    @Override
    public ResponseDto changeMyDetails(UserDetailedInfoDto dto, String userName) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findByUsername(userName).orElse(null);
        if (isNull(user)) {
            responseDto.setStatus(NOT_FOUND.value());
            responseDto.setSuccess(false);
            return responseDto;
        }
        Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findByUser(user);

        if (!dto.getUsername().isEmpty() && dto.getUsername() != null && userDetailedInfo.isPresent()) {
            if (userRepository.existsByUsername(dto.getUsername())) {
                return ResponseDto.builder()
                        .status(BAD_REQUEST.value())
                        .errorMessage(ErrorMessages.userWithLoginExists(dto.getUsername())).build();
            }
            user.setUsername(dto.getUsername());
            user.setVerified(false);
            user = userRepository.save(user);
            userDetailedInfo.get().setUser(user);
            String uuid = UUID.randomUUID().toString();
            emailService.sendEmail(user, uuid);
            responseDto.setSuccess(true);
            responseDto.setData(uuid);
        }
        if (!dto.getFirstname().isEmpty() && dto.getFirstname() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setFirstname(dto.getFirstname());
        }
        if (!dto.getLastname().isEmpty() && dto.getLastname() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setLastname(dto.getLastname());
        }
        if (!dto.getPatronymic().isEmpty() && dto.getPatronymic() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setPatronymic(dto.getPatronymic());
        }
        if (!dto.getGender().isEmpty() && dto.getGender() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setGender(Gender.valueOf(dto.getGender()));
        }
        if (!dto.getAbout().isEmpty() && dto.getAbout() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setAbout(dto.getAbout());
        }
        if (!dto.getPhone().isEmpty() && dto.getPhone() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setPhone(dto.getPhone());
        }
        if (!dto.getCountry().isEmpty() && dto.getCountry() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setCountry(dto.getCountry());
        }
        if (!dto.getJob().isEmpty() && dto.getJob() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setJob(dto.getJob());
        }
        if (dto.getBirth() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setBirth(dto.getBirth());
        }
        userDetailedInfo.ifPresent(detailedInfoRepository::save);
        responseDto.setSuccess(true);
        return responseDto;
    }

    @Override
    public ResponseDto getInfoByUsername(String username) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findByUsername(username).orElse(null);
        if (isNull(user)) {
            responseDto.setStatus(NOT_FOUND.value());
            responseDto.setSuccess(false);
            return responseDto;
        }
        Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findByUser(user);
        if (userDetailedInfo.isPresent()) {
            UserDetailedInfoDto dto = ObjectsMapper.convertToUserDetailedInfoDto(userDetailedInfo.get());
            dto.setUsername(user.getUsername());
           responseDto.setSuccess(true);
           responseDto.setStatus(OK.value());
           responseDto.setData(dto);
           return responseDto;
        }
        responseDto.setStatus(NOT_FOUND.value());
        responseDto.setSuccess(false);
        return responseDto;
    }
}
