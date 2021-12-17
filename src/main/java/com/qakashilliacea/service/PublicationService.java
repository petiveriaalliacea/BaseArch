package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.PublicationDto;
import com.qakashilliacea.web.dto.ResponseDto;

import java.security.Principal;
import java.time.LocalDate;

public interface PublicationService {
    ResponseDto getById(Long id);

    ResponseDto getByUserId(Long userId);

    ResponseDto findAll();

    ResponseDto findByPublicationDate(LocalDate date);

    ResponseDto deleteById(Long id);

    ResponseDto create(PublicationDto publication, Principal principal);
}
