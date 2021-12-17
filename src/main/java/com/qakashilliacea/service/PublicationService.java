package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.PageableDto;
import com.qakashilliacea.web.dto.PublicationDto;
import com.qakashilliacea.web.dto.ResponseDto;

import java.security.Principal;

public interface PublicationService {
    ResponseDto getById(Long id);

    ResponseDto getAllByUserId(Long userId, PageableDto dto);

    ResponseDto findAll();

    ResponseDto deleteById(Long id);

    ResponseDto create(PublicationDto publication, Principal principal);
}
