package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "PublicationDto", description = "gets name , description to create publication")
public class PublicationDto {
    private String name;
    private String description;
    private Long typeId;
}
