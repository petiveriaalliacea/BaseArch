package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "UserRegistrationInfoDto")
public class RegisterDto {
    private String username;
    private String password;
}
