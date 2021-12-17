package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "UserDetailsDto", description = "Dto for user data")
public class UserDto {
    @ApiModelProperty(value = "Username", required = true)
    private String username;
    @ApiModelProperty(value = "Password", required = false)
    private String password;
}
