package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@ApiModel(value = "PublicationInfoDto", description = "returns publication info")
public class PublicationInfoDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private Integer views;
    private UserDto userDto;
}
