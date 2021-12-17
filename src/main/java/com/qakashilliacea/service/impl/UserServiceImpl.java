package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.EmailVerification;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.EmailVerificationRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.UserService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.ResponseDto;
import com.qakashilliacea.web.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    @Override
    public ResponseDto createUser(UserDto dto) {
        ResponseDto response = new ResponseDto<UserDto>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            response.setStatus(400);
            response.setErrorMessage(ErrorMessages.userWithLoginExists(dto.getUsername()));
            return response;
        }

        response.setSuccess(true);
        response.setData(userRepository.save(ObjectsMapper.converToUser(dto)).getId());
        return response;
    }

    @Override
    public ResponseDto updateUser(UserDto dto, Long id) {
        ResponseDto response = new ResponseDto<UserDto>();
        if (!userRepository.existsById(id)) {
            response.setStatus(404);
            response.setErrorMessage(ErrorMessages.cantFindEntityById("User", id));
            return response;
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            response.setStatus(400);
            response.setErrorMessage(ErrorMessages.userWithLoginExists(dto.getUsername()));
            return response;
        }

        User user = userRepository.getById(id);
        response.setSuccess(true);
        response.setData(updateUser(user, dto));
        return response;
    }

    private UserDto updateUser(User user, UserDto dto) {

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        user = userRepository.save(user);

        return ObjectsMapper.converToUserDto(user);
    }

    @Override
    public ResponseDto readUser(Long id) {
        ResponseDto response = new ResponseDto<UserDto>();
        if (!userRepository.existsById(id)) {
            response.setStatus(404);
            response.setErrorMessage(ErrorMessages.cantFindEntityById("User", id));
            return response;
        }

        response.setSuccess(true);
        response.setData(ObjectsMapper.converToUserDto(userRepository.getById(id)));
        return response;
    }

    @Override
    public ResponseDto deleteUser(Long id) {
        ResponseDto response = new ResponseDto<UserDto>();
        if (!userRepository.existsById(id)) {
            response.setStatus(404);
            response.setErrorMessage(ErrorMessages.cantFindEntityById("User", id));
            return response;
        }
        EmailVerification emailVerification = emailVerificationRepository.findEmailVerificationByUserId(id);
        emailVerificationRepository.deleteById(emailVerification.getId());
        UserDto dto = ObjectsMapper.converToUserDto(userRepository.getById(id));
        userRepository.deleteById(id);
        response.setSuccess(true);
        response.setData(dto);
        return response;
    }
}
