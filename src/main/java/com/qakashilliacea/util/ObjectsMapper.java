package com.qakashilliacea.util;

import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.Role;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.web.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

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

    public static RoleDto converToRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static PublicationDto convertToPublicationDto(Optional<Publication> publication) {
        return PublicationDto.builder().id(publication.get().getId()).name(publication.get().getName())
                .description(publication.get().getDescription()).createdAt(publication.get().getCreatedAt())
                .views(publication.get().getViews()).userDto(convertToUserDtoWithoutPass(publication.get().getUser())).build();
    }

    public static Publication convertToPublication(PublicationDto publicationDto) {
        return Publication.builder().name(publicationDto.getName())
                .description(publicationDto.getDescription()).build();
    }

    public static Publication convertPublicationCreatorToPublication(PublicationCreatorDto publicationCreatorDto) {
        return Publication.builder().name(publicationCreatorDto.getName())
                .description(publicationCreatorDto.getDescription()).build();
    }

    public static PublicationDto convertToPublic(Publication publication) {
        return PublicationDto.builder().id(publication.getId()).name(publication.getName())
                .description(publication.getDescription()).createdAt(publication.getCreatedAt())
                .views(publication.getViews()).userDto(convertToUserDtoWithoutPass(publication.getUser())).build();
    }

    public static UserDto convertToUserDtoWithoutPass(User user) {
        return UserDto.builder().username(user.getUsername()).build();
    }
}
