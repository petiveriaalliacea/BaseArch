package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "dto to create publication")
public class PublicationCreatorDto {
    private String name;
    private String description;
}
