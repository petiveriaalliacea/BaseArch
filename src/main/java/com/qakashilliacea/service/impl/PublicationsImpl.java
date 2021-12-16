package com.qakashilliacea.service.impl;

import com.qakashilliacea.config.security.PrincipalImpl;
import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.PublicationRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.PublicationService;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.PublicationCreatorDto;
import com.qakashilliacea.web.dto.PublicationDto;
import com.qakashilliacea.web.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class PublicationsImpl implements PublicationService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Override
    public ResponseDto create(PublicationCreatorDto publicationCreatorDto, Principal principal) {
        User userObj = userRepository.findByUsername(principal.getName());
        ResponseDto responseDto = new ResponseDto<PublicationDto>();
        if (nonNull(publicationCreatorDto.getDescription()) && nonNull(publicationCreatorDto.getDescription())) {
            Publication publication = ObjectsMapper.convertPublicationCreatorToPublication(publicationCreatorDto);
            publication.setCreatedAt(LocalDate.now());
            publication.setViews(0);
            publication.setUserId(userObj.getId());
            publicationRepository.save(publication);
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(406);
        responseDto.setErrorMessage("Not Acceptable");
        return responseDto;
    }

    @Override
    public ResponseDto deleteById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        if (publicationRepository.findPublicationById(id).isPresent()) {
            publicationRepository.deleteById(id);
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(404);
        responseDto.setErrorMessage("Not Found");
        return responseDto;
    }

    @Override
    public ResponseDto getById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        if (publicationRepository.findPublicationById(id).isPresent()) {
            Optional<Publication> publication = publicationRepository.findPublicationById(id);
            PublicationDto publicationDto = ObjectsMapper.convertToPublicationDto(publication);
            responseDto.setData(publicationDto);
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(404);
        responseDto.setErrorMessage("Not Found");
        return responseDto;
    }

    @Override
    public ResponseDto getByUserId(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        if (publicationRepository.findPublicationByUserId(userId).isPresent()) {
            Optional<Publication> publication = publicationRepository.findPublicationByUserId(userId);
            PublicationDto publicationDto = ObjectsMapper.convertToPublicationDto(publication);
            responseDto.setData(publicationDto);
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(404);
        responseDto.setErrorMessage("Not Found");
        return responseDto;
    }

    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto = new ResponseDto();
        List<PublicationDto> publicationDtoList = new ArrayList<>();
        for (Publication publication : publicationRepository.findAll()) {
            PublicationDto publicationDto = ObjectsMapper.convertToPublic(publication);
            publicationDtoList.add(publicationDto);
        }
        responseDto.setSuccess(true);
        responseDto.setData(publicationDtoList);
        return responseDto;
    }

    @Override
    public ResponseDto findByPublicationDate(LocalDate date) {
        ResponseDto responseDto = new ResponseDto();
        if (publicationRepository.findPublicationByCreatedAt(date).isPresent()) {
            Optional<Publication> publication = publicationRepository.findPublicationByCreatedAt(date);
            PublicationDto publicationDto = ObjectsMapper.convertToPublicationDto(publication);
            responseDto.setData(publicationDto);
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setSuccess(false);
        responseDto.setStatus(404);
        responseDto.setErrorMessage("Not found");
        return responseDto;
    }
}
