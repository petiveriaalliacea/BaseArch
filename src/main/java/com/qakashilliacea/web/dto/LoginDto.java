package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "LoginDto", description = "user data to login")
public class LoginDto {
    private String username;
    private String password;
}
