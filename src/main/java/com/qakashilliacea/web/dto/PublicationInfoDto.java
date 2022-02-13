package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ApiModel(value = "PublicationInfoDto", description = "returns publication info")
public class PublicationInfoDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Integer views;
    private Integer amountOfLikes;
    private Long typeId;
    private Long userId;
}
