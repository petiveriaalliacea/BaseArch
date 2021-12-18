package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.ResponseDto;

public interface PublicationLikesService {
    ResponseDto addLikeToPublication(Long publicationId);

    ResponseDto deleteLikeFromPublication(Long publicationId);
}
