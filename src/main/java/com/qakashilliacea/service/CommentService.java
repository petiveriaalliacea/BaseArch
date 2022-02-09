package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.CommentDto;
import com.qakashilliacea.web.dto.PageableDto;
import com.qakashilliacea.web.dto.ResponseDto;

import java.security.Principal;

public interface CommentService {

    ResponseDto create(CommentDto commentDto, Principal principal);

    ResponseDto findAllByPublicationId(Long id, PageableDto dto);

    ResponseDto findAllParentsByPublicationId (Long publicationId, PageableDto dto);

    ResponseDto findAllChildByParentId (Long parentId, PageableDto dto);

    ResponseDto findAllPageableParentsWithChildren (Long publicationId, PageableDto dto);

}
