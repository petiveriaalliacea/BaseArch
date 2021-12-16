package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "dto to log in")
public class LoginDto {
    private String username;
    private String password;
}
