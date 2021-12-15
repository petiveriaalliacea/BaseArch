package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ResponseDto", description = "Dto for all responses")
public class ResponseDto<T> {
    @ApiModelProperty(value = "Is request success", required = true)
    private Boolean success = false;
    @ApiModelProperty(value = "Http Status Id", required = true)
    private Integer status = 200;
    @ApiModelProperty(value = "Message if error occurs", required = false)
    private String errorMessage;
    @ApiModelProperty(value = "Responding data", required = false)
    private T data;
}
