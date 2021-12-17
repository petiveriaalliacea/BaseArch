package com.qakashilliacea.util;


import com.qakashilliacea.web.dto.PageableDto;
import org.springframework.data.domain.PageRequest;

public class PageUtils {
    public static PageRequest buildPageable(PageableDto dto) {
        return PageRequest.of(dto.getPageNumber(), dto.getPageSize(), dto.getDirection(), dto.getSortBy());
    }

    private PageUtils () {}
}
