package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.PublicationLikes;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.PublicationLikesRepository;
import com.qakashilliacea.respository.PublicationRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CurrentUserService;
import com.qakashilliacea.service.PublicationLikesService;
import com.qakashilliacea.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class PublicationLikesImpl implements PublicationLikesService {
    private final PublicationLikesRepository likesRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto addLikeToPublication(Long publicationId, String username) {
        ResponseDto responseDto = new ResponseDto<>();
        User user = userRepository.findByUsername(username).orElse(null);
        if (isNull(user)) {
            responseDto.setStatus(NOT_FOUND.value());
            responseDto.setSuccess(false);
            return responseDto;
        }
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<PublicationLikes> publicationLike = likesRepository
                .findByPublicationIdAndCreatedBy(publicationId, user);
        if (publicationLike.isPresent()) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setSuccess(false);
            return responseDto;
        }
        if (publication.isPresent()) {
            publication.get().setAmountOfLikes(publication.get().getAmountOfLikes() + 1);
            PublicationLikes publicationLikes = PublicationLikes.builder().publicationId(publicationId).build();
            likesRepository.save(publicationLikes);
            publicationRepository.save(publication.get());
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setStatus(NOT_FOUND.value());
        return responseDto;
    }

    @Override
    public ResponseDto deleteLikeFromPublication(Long publicationId, String username) {
        ResponseDto responseDto = new ResponseDto<>();
        User user = userRepository.findByUsername(username).orElse(null);
        if (isNull(user)) {
            responseDto.setStatus(NOT_FOUND.value());
            responseDto.setSuccess(false);
            return responseDto;
        }
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<PublicationLikes> publicationLike = likesRepository.findByPublicationIdAndCreatedBy
                (publicationId, user);
        if (publicationLike.isEmpty()) {
            responseDto.setStatus(BAD_REQUEST.value());
            return responseDto;
        }
        if (publication.isPresent()) {
            publication.get().setAmountOfLikes(publication.get().getAmountOfLikes() - 1);
            likesRepository.deleteByPublicationId(publicationId);
            publicationRepository.save(publication.get());
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setStatus(NOT_FOUND.value());
        return responseDto;
    }
}
