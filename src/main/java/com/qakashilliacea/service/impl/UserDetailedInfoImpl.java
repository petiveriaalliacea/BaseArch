package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.entity.UserDetailedInfo;
import com.qakashilliacea.respository.UserDetailedInfoRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CurrentUserService;
import com.qakashilliacea.service.EmailSenderService;
import com.qakashilliacea.service.UserDetailedInfoService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.ResponseDto;
import com.qakashilliacea.web.dto.UserDetailedInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.qakashilliacea.util.constants.ErrorConstants.BAD_REQUEST;
import static com.qakashilliacea.util.constants.ErrorConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailedInfoImpl implements UserDetailedInfoService {
    private final UserDetailedInfoRepository detailedInfoRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final EmailSenderService emailSenderService;

    @Override
    public ResponseDto getByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findByUser(user.get());
            if (userDetailedInfo.isPresent()) {
                UserDetailedInfoDto userDetailedInfoDto = ObjectsMapper.convertToUserDetailedInfoDto(userDetailedInfo.get());
                return ResponseDto.builder()
                        .success(true)
                        .data(userDetailedInfoDto).build();
            }
        }
        return ResponseDto.builder()
                .status(NOT_FOUND).build();
    }

    @Override
    public ResponseDto getById(Long id) {
        Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findById(id);
        if (userDetailedInfo.isPresent()) {
            UserDetailedInfoDto userDetailedInfoDto = ObjectsMapper.convertToUserDetailedInfoDto(userDetailedInfo.get());
            return ResponseDto.builder()
                    .success(true)
                    .data(userDetailedInfoDto).build();
        }
        return ResponseDto.builder()
                .status(NOT_FOUND).build();
    }

    @Override
    public ResponseDto changeDetails(UserDetailedInfoDto dto) {
        User user = currentUserService.getCurrentUser();
        Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findByUser(user);
        ResponseDto responseDto = new ResponseDto();
        if (!dto.getUsername().isEmpty() && dto.getUsername() != null && userDetailedInfo.isPresent()) {
            if (userRepository.existsByUsername(dto.getUsername())) {
                return ResponseDto.builder()
                        .status(BAD_REQUEST)
                        .errorMessage(ErrorMessages.userWithLoginExists(dto.getUsername())).build();
            }
            user.setUsername(dto.getUsername());
            user.setIsVerified(false);
            user = userRepository.save(user);
            userDetailedInfo.get().setUser(user);
            String uuid = UUID.randomUUID().toString();
            emailSenderService.sendEmail(user, uuid);
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
        if (!dto.getSex().isEmpty() && dto.getSex() != null && userDetailedInfo.isPresent()) {
            userDetailedInfo.get().setSex(dto.getSex());
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
    public ResponseDto getByLoggedUserInfo() {
        User user = currentUserService.getCurrentUser();
        Optional<UserDetailedInfo> userDetailedInfo = detailedInfoRepository.findByUser(user);
        if (userDetailedInfo.isPresent()) {
            UserDetailedInfoDto dto = ObjectsMapper.convertToUserDetailedInfoDto(userDetailedInfo.get());
            return ResponseDto.builder()
                    .success(true)
                    .data(dto).build();
        }
        return ResponseDto.builder()
                .status(BAD_REQUEST).build();
    }
}
