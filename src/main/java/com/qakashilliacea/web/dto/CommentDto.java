package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "CommentDto", description = "gets description to create publication")
public class CommentDto {
    private String description;
    private Long publicationId;
    private Long parentId;
}
