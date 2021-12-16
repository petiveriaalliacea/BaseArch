package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Dto for auth token")
public class AuthResponseDto {
    private String accessToken;
}
