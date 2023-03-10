package com.qakashilliacea.util;

import com.qakashilliacea.entity.Order;
import com.qakashilliacea.entity.Comment;
import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.Role;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.entity.UserDetailedInfo;
import com.qakashilliacea.entity.enums.Gender;
import com.qakashilliacea.web.dto.*;
import lombok.experimental.UtilityClass;

import java.util.HashSet;

@UtilityClass
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


    public static Publication convertToPublication(PublicationInfoDto publicationInfoDto) {
        return Publication.builder().name(publicationInfoDto.getName())
                .description(publicationInfoDto.getDescription()).build();
    }

    public static Publication convertPublicationCreatorToPublication(PublicationDto publicationDto) {
        return Publication.builder().name(publicationDto.getName())
                .description(publicationDto.getDescription()).typeId(publicationDto.getTypeId()).build();
    }

    public static PublicationInfoDto convertToPublicationInfoDto(Publication publication) {
        return PublicationInfoDto.builder()
                .id(publication.getId())
                .name(publication.getName())
                .description(publication.getDescription())
                .createdAt(publication.getCreatedDate())
                .views(publication.getViews())
                .userId(publication.getUserId())
                .amountOfLikes(publication.getAmountOfLikes())
                .typeId(publication.getTypeId())
                .build();
    }

    public static UserDto convertToUserDtoWithoutPass(User user) {
        return UserDto.builder().username(user.getUsername()).build();
    }

    public static OrderInfoDto converToOrderInfoDto (Order order) {
        return OrderInfoDto.builder()
                .orderStatus(order.getOrderStatus())
                .creationDate(order.getCreatedDate())
                .userId(order.getCreatedBy().getId())
                .productId(order.getProductId())
                .build();
    }

    public static Order convertToOrder (OrderDto dto) {
        return Order.builder().productId(dto.getProductId()).build();
    }

    public static Comment convertCommentCreatorToComment(CommentDto commentDto){
        return Comment.builder()
                .description(commentDto.getDescription())
                .publicationId(commentDto.getPublicationId())
                .parentId(commentDto.getParentId())
                .build();
    }

    public static CommentInfoDto convertToCommentInfoDto(Comment comment) {
        return CommentInfoDto.builder()
                .id(comment.getId())
                .description(comment.getDescription())
                .userId(comment.getCreatedBy().getId())
                .publicationId(comment.getPublicationId())
                .createdDate(comment.getCreatedDate())
                .build();
    }

    public static UserDetailedInfo convertToUserDetailedInfo(UserRegistrationInfoDto dto) {
        return UserDetailedInfo.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .patronymic(dto.getPatronymic())
                .phone(dto.getPhone())
                .country(dto.getCountry())
                .birth(dto.getBirth())
                .gender(Gender.valueOf(dto.getSex()))
                .about(dto.getAbout())
                .job(dto.getJob()).build();
    }

    public static UserDetailedInfoDto convertToUserDetailedInfoDto(UserDetailedInfo dao) {
        return UserDetailedInfoDto.builder()
                .firstname(dao.getFirstname())
                .lastname(dao.getLastname())
                .patronymic(dao.getPatronymic())
                .phone(dao.getPhone())
                .country(dao.getCountry())
                .birth(dao.getBirth())
                .gender(dao.getGender().name())
                .about(dao.getAbout())
                .job(dao.getJob()).build();
    }
}
