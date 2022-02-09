package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.PublicationDto;
import com.qakashilliacea.web.dto.PublicationSortDto;
import com.qakashilliacea.web.dto.ResponseDto;

import java.security.Principal;

public interface PublicationService {
    ResponseDto getById(Long id);

    ResponseDto findAll();

    ResponseDto deleteById(Long id);

    ResponseDto create(PublicationDto publication, Principal principal);

    ResponseDto getAllSorted(PublicationSortDto dto);
}
