package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "RoleDto", description = "returns role details of user")
public class RoleDto {
    private Long id;
    private String name;
}
