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
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.qakashilliacea.util.constants.ErrorConstants.BAD_REQUEST;
import static com.qakashilliacea.util.constants.ErrorConstants.NOT_FOUND;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class PublicationsImpl implements PublicationService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final PublicationTypeRepository publicationTypeRepository;

    @Override
    public ResponseDto create(PublicationDto publicationDto, Principal principal) {
        User userObj = userRepository.findByUsername(principal.getName()).get();
        Optional<PublicationType> publicationType = publicationTypeRepository.findById(publicationDto.getTypeId());
        ResponseDto responseDto = new ResponseDto<PublicationInfoDto>();
        if (isNull(publicationDto.getDescription())) {
            responseDto.setStatus(BAD_REQUEST);
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("description"));
            return responseDto;
        }
        if (isNull(publicationDto.getName())) {
            responseDto.setStatus(BAD_REQUEST);
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("name"));
            return responseDto;
        }
        if (publicationType.isEmpty()) {
            responseDto.setStatus(BAD_REQUEST);
            return responseDto;
        }
        Publication publication = ObjectsMapper.convertPublicationCreatorToPublication(publicationDto);
        publication.setCreatedAt(LocalDate.now());
        publication.setViews(0);
        publication.setUserId(userObj.getId());
        publicationRepository.save(publication);
        responseDto.setSuccess(true);
        responseDto.setData(ObjectsMapper.convertToPublicationDto(publication));
        return responseDto;
    }

    @Override
    public ResponseDto deleteById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        if (publicationRepository.findById(id).isPresent()) {
            publicationRepository.deleteById(id);
            responseDto.setSuccess(true);
            return responseDto;
        } else {
            responseDto.setSuccess(false);
            responseDto.setStatus(NOT_FOUND);
            responseDto.setErrorMessage(ErrorMessages.cantFindEntityById(Publication.class, id));
            return responseDto;
        }
    }

    @Override
    public ResponseDto getById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent()) {
            PublicationInfoDto publicationInfoDto = ObjectsMapper.convertToPublicationDto(publication.get());
            responseDto.setData(publicationInfoDto);
            responseDto.setSuccess(true);
            return responseDto;
        } else {
            responseDto.setSuccess(false);
            responseDto.setStatus(NOT_FOUND);
            responseDto.setErrorMessage("Not Found");
            return responseDto;
        }
    }

    @Override
    public ResponseDto getAllByUserId(Long userId, PageableDto dto) {
        Page<Publication> page = publicationRepository.findAllByUserId(userId, PageUtils.buildPageable(dto));
        List<PublicationInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToPublicationDto)
                .collect(Collectors.toList());

        return ResponseDto.builder()
                .success(true)
                .data(new PageDto(page, dtoList))
                .build();
    }

    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto = new ResponseDto();
        List<PublicationInfoDto> publicationInfoDtoList = publicationRepository.findAll()
                .stream()
                .map(ObjectsMapper::convertToPublicationDto)
                .collect(Collectors.toList());
        responseDto.setSuccess(true);
        responseDto.setData(publicationInfoDtoList);
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageableByDate(PageableDto dto) {
        Page<Publication> page = publicationRepository.findAll(
                PublicationSpecification.getAllPageableByDate(), PageUtils.buildPageable(dto));
        List<PublicationInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToPublicationDto).collect(Collectors.toList());
        return ResponseDto.builder()
                .success(true)
                .data(new PageDto(page, dtoList)).build();
    }

    @Override
    public ResponseDto getAllPageableByPublicationType(String type, PageableDto dto) {
        Optional<PublicationType> publicationType = publicationTypeRepository.findByType(type.toLowerCase());
        if (publicationType.isPresent()) {
            Page<Publication> page = publicationRepository.findAll(
                    PublicationSpecification.getAllPageableByPublicationType(publicationType.get().getId()),
                    PageUtils.buildPageable(dto));
            List<PublicationInfoDto> dtoList = page
                    .stream()
                    .map(ObjectsMapper::convertToPublicationDto).collect(Collectors.toList());
            return ResponseDto.builder()
                    .success(true).data(new PageDto(page, dtoList)).build();
        }
        return ResponseDto.builder()
                .status(NOT_FOUND).build();
    }

    @Override
    public ResponseDto getAllPageableByUserId(Long userId, PageableDto dto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Page<Publication> page = publicationRepository.findAll(
                    PublicationSpecification.getAllPageableByUserId(userId),
                    PageUtils.buildPageable(dto));
            List<PublicationInfoDto> dtoList = page
                    .stream()
                    .map(ObjectsMapper::convertToPublicationDto).collect(Collectors.toList());
            return ResponseDto.builder()
                    .success(true).data(new PageDto(page, dtoList)).build();
        }
        return ResponseDto.builder().status(NOT_FOUND).build();
    }
}
