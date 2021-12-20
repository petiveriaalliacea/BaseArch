package com.qakashilliacea.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ApiModel(value = "CommentInfoDto", description = "returns publication info")
public class CommentInfoDto {
    private Long id;
    private String description;
    private Long userId;
    private Long publicationId;
    private LocalDateTime createdDate;
}
