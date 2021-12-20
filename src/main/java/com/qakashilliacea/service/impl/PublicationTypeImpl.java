package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.PublicationType;
import com.qakashilliacea.respository.PublicationTypeRepository;
import com.qakashilliacea.service.PublicationTypeService;
import com.qakashilliacea.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.qakashilliacea.util.constants.ErrorConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PublicationTypeImpl implements PublicationTypeService {
    private final PublicationTypeRepository publicationTypeRepository;

    @Override
    public ResponseDto getPublicationTypeInfoById(Long id) {
        ResponseDto responseDto = new ResponseDto<>();
        Optional<PublicationType> publicationType = publicationTypeRepository.findById(id);
        if (publicationType.isPresent()) {
            responseDto.setSuccess(true);
            responseDto.setData(publicationType);
            return responseDto;
        }
        responseDto.setStatus(NOT_FOUND);
        return responseDto;
    }
}
