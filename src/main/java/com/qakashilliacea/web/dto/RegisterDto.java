package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "RegisterDto", description = "data to registration")
public class RegisterDto {
    private String username;
    private String password;
}
