package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.PublicationLikes;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.PublicationLikesRepository;
import com.qakashilliacea.respository.PublicationRepository;
import com.qakashilliacea.service.CurrentUserService;
import com.qakashilliacea.service.PublicationLikesService;
import com.qakashilliacea.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.qakashilliacea.util.constants.ErrorConstants.BAD_REQUEST;
import static com.qakashilliacea.util.constants.ErrorConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PublicationLikesImpl implements PublicationLikesService {
    private final CurrentUserService currentUserService;
    private final PublicationLikesRepository likesRepository;
    private final PublicationRepository publicationRepository;

    @Override
    public ResponseDto addLikeToPublication(Long publicationId) {
        ResponseDto responseDto = new ResponseDto<>();
        User user = currentUserService.getCurrentUser();
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<PublicationLikes> publicationLike = likesRepository
                .findByPublicationIdAndCreatedBy(publicationId, user);
        if (publicationLike.isPresent()) {
            responseDto.setStatus(BAD_REQUEST);
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
        responseDto.setStatus(NOT_FOUND);
        return responseDto;
    }

    @Override
    public ResponseDto deleteLikeFromPublication(Long publicationId) {
        ResponseDto responseDto = new ResponseDto<>();
        User user = currentUserService.getCurrentUser();
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        Optional<PublicationLikes> publicationLike = likesRepository.findByPublicationIdAndCreatedBy
                (publicationId, user);
        if (publicationLike.isEmpty()) {
            responseDto.setStatus(BAD_REQUEST);
            return responseDto;
        }
        if (publication.isPresent()) {
            publication.get().setAmountOfLikes(publication.get().getAmountOfLikes() - 1);
            likesRepository.deleteByPublicationId(publicationId);
            publicationRepository.save(publication.get());
            responseDto.setSuccess(true);
            return responseDto;
        }
        responseDto.setStatus(NOT_FOUND);
        return responseDto;
    }
}
