package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.PublicationType;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.PublicationRepository;
import com.qakashilliacea.respository.PublicationTypeRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.respository.filter.PublicationSpecification;
import com.qakashilliacea.service.PublicationService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.util.PageUtils;
import com.qakashilliacea.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final PublicationTypeRepository publicationTypeRepository;

    @Override
    public ResponseDto create(PublicationDto publicationDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        Optional<PublicationType> publicationType = publicationTypeRepository.findById(publicationDto.getTypeId());
        ResponseDto responseDto = new ResponseDto();
        if (isNull(publicationDto.getDescription())) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("description"));
            return responseDto;
        }
        if (isNull(publicationDto.getName())) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("name"));
            return responseDto;
        }
        if (isNull(publicationDto.getTypeId())) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("typeId"));
            return responseDto;
        }
        if (!publicationType.isPresent()) {
            responseDto.setStatus(NOT_FOUND.value());
            responseDto.setErrorMessage(ErrorMessages.cantFindEntityById(PublicationType.class, publicationDto.getTypeId()));
            return responseDto;
        }

        Publication publication = ObjectsMapper.convertPublicationCreatorToPublication(publicationDto);
        publication.setCreatedDate(LocalDateTime.now());
        publication.setViews(0);
        publication.setUserId(user.getId());
        publicationRepository.save(publication);

        responseDto.setSuccess(true);
        responseDto.setData(ObjectsMapper.convertToPublicationInfoDto(publication));
        return responseDto;
    }

    @Override
    public ResponseDto deleteById(Long id) {
        ResponseDto responseDto = new ResponseDto();

        if (publicationRepository.findById(id).isPresent()) {
            publicationRepository.deleteById(id);
            responseDto.setSuccess(true);
            return responseDto;
        }

        responseDto.setSuccess(false);
        responseDto.setStatus(NOT_FOUND.value());
        responseDto.setErrorMessage(ErrorMessages.cantFindEntityById(Publication.class, id));
        return responseDto;
    }

    @Override
    public ResponseDto getById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Publication> publication = publicationRepository.findById(id);

        if (publication.isPresent()) {
            PublicationInfoDto publicationInfoDto = ObjectsMapper.convertToPublicationInfoDto(publication.get());
            responseDto.setData(publicationInfoDto);
            responseDto.setSuccess(true);
            return responseDto;
        }

        responseDto.setSuccess(false);
        responseDto.setStatus(NOT_FOUND.value());
        responseDto.setErrorMessage("Not Found");
        return responseDto;
    }

    public ResponseDto findAll() {
        ResponseDto responseDto = new ResponseDto();
        List<PublicationInfoDto> publicationInfoDtoList = publicationRepository.findAll()
                .stream()
                .map(ObjectsMapper::convertToPublicationInfoDto)
                .collect(Collectors.toList());
        responseDto.setSuccess(true);
        responseDto.setData(publicationInfoDtoList);
        return responseDto;
    }

    @Override
    public ResponseDto getAllSorted(PublicationSortDto dto) {
        Specification<Publication> spec = null;
        if (nonNull(dto.getUserId())) {
            spec = PublicationSpecification.authorIdEquals(dto.getUserId());
        }
        if (nonNull(dto.getPublicationTypeId())) {
            spec = isNull(spec) ? PublicationSpecification.typeIdEquals(dto.getPublicationTypeId()) : spec.and(PublicationSpecification.typeIdEquals(dto.getPublicationTypeId()));
        }

        Page<Publication> page = publicationRepository.findAll(spec, PageUtils.buildPageable(dto));
        List<PublicationInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToPublicationInfoDto).collect(Collectors.toList());
        return ResponseDto.builder()
                .success(true).data(new PageDto(page, dtoList)).build();
    }
}
