package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@ApiModel(value = "Dto for publications")
public class PublicationDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private Integer views;
    private UserDto userDto;
}
