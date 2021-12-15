package com.qakashilliacea.service.impl;

import com.qakashilliacea.config.security.JwtCoder;
import com.qakashilliacea.config.security.PasswordEncoder;
import com.qakashilliacea.entity.Role;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.util.ExceptionAnswers;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public ResponseDto signUp(RegisterDto dto) {
        ResponseDto response = new ResponseDto<>();
        if (userRepository.existsByUsername(dto.getUsername())) {
            response.setStatus(400);
            response.setErrorMessage(ExceptionAnswers.userWithLoginExists(dto.getUsername()));
            return response;
        }
        dto.setPassword(PasswordEncoder.encode(dto.getPassword()));
        User user = ObjectsMapper.converToUser(dto);
        user.getRoles().add(roleRepository.getById(Role.ROLE_USER));
        user = userRepository.save(user);
        response.setSuccess(true);
        response.setData(AuthResponseDto.builder().accessToken(JwtCoder.generateJwt(user)).build());
        return response;
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
        if (!user.getPassword().equals(PasswordEncoder.encode(dto.getPassword()))) {
            response.setStatus(400);
            response.setErrorMessage(ExceptionAnswers.incorrectPassword());
            return response;
        } else {
            response.setSuccess(true);
            response.setData(AuthResponseDto.builder().accessToken(JwtCoder.generateJwt(user)).build());
            return response;
        }
    }
}
