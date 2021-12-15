package com.qakashilliacea.util;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.web.dto.RegisterDto;
import com.qakashilliacea.web.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ObjectsMapper {
    public static User converToUser(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
    public static UserDto converToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
    public static User converToUser(RegisterDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(new HashSet<>())
                .build();
    }
}
