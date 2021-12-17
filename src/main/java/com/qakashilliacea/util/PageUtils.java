package com.qakashilliacea.util;


import com.qakashilliacea.web.dto.PageableDto;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;

@UtilityClass
public class PageUtils {
    public static PageRequest buildPageable(PageableDto dto) {
        return PageRequest.of(dto.getPageNumber(), dto.getPageSize(), dto.getDirection(), dto.getSortBy());
    }
}
