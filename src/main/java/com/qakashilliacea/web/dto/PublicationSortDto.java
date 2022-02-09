package com.qakashilliacea.web.dto;

import lombok.Data;

@Data
public class PublicationSortDto extends PageableDto {
    private Long userId;
    private Long publicationTypeId;
}
