package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "UserDto", description = "Dto for user info")
public class UserDto {
    @ApiModelProperty(value = "Username", required = true)
    private String username;
    @ApiModelProperty(value = "Password", required = false)
    private String password;
}
