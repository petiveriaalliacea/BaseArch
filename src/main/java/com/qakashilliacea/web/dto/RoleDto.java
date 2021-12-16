package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Dto roles", description = "returns id and name of role")
public class RoleDto {
    private Long id;
    private String name;
}
