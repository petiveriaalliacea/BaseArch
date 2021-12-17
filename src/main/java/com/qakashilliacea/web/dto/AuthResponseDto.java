package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Dto to return auth token")
public class AuthResponseDto {
    private String accessToken;
}
