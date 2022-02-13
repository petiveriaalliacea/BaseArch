package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Comment;
import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.respository.CommentRepository;
import com.qakashilliacea.respository.PublicationRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CommentService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.util.PageUtils;
import com.qakashilliacea.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseDto create(CommentDto dto, Principal principal) {
        ResponseDto responseDto = new ResponseDto<CommentInfoDto>();

        if (isNull(dto.getPublicationId())) {
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("publicationId"));
            return responseDto;
        }

        if(isNull(dto.getDescription())){
            responseDto.setStatus(BAD_REQUEST.value());
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("description"));
            return responseDto;
        }

        Optional<Publication> publication = publicationRepository.findById(dto.getPublicationId());
        if (!publication.isPresent()) {
            responseDto.setStatus(NOT_FOUND.value());
            responseDto.setErrorMessage(ErrorMessages.cantFindEntityById(Publication.class, dto.getPublicationId()));
            return responseDto;
        }

        Comment comment = ObjectsMapper.convertCommentCreatorToComment(dto);

        commentRepository.save(comment);
        responseDto.setSuccess(true);
        responseDto.setData(ObjectsMapper.convertToCommentInfoDto(comment));
        return responseDto;
    }

    @Override
    public ResponseDto findAllByPublicationId(Long publicationId, PageableDto dto) {
        ResponseDto responseDto = new ResponseDto();
        Page<Comment> page = commentRepository.findAllByPublication_Id(publicationId, PageUtils.buildPageable(dto));

        List<CommentInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToCommentInfoDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .success(true)
                .data(new PageDto(page, dtoList))
                .build();
    }

    @Override
    public ResponseDto findAllParentsByPublicationId(Long publicationId, PageableDto dto) {
        ResponseDto responseDto = new ResponseDto();
        Page<Comment> page = commentRepository.findAllByPublication_IdAndParentIdIsNull(publicationId, PageUtils.buildPageable(dto));

        List<CommentInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToCommentInfoDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .success(true)
                .data(new PageDto(page, dtoList))
                .build();
    }

    @Override
    public ResponseDto findAllChildByParentId(Long parentId, PageableDto dto) {
        ResponseDto responseDto = new ResponseDto();
        Page<Comment> page = commentRepository.findAllByParentIdEquals(parentId, PageUtils.buildPageable(dto));

        List<CommentInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToCommentInfoDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .success(true)
                .data(new PageDto(page, dtoList))
                .build();
    }

    @Override
    public ResponseDto findAllPageableParentsWithChildren(Long publicationId, PageableDto dto) {
        ResponseDto responseDto = new ResponseDto();
        Page<Comment> page = commentRepository.findAllByPublication_IdAndParentIdIsNull(publicationId, PageUtils.buildPageable(dto));
        List<Long> parentCommentIds = new ArrayList<>();
        Map<Long, List<CommentInfoDto>> commentsMap = new HashMap<>();
        List<ParentChildCommentDto> returnList = new ArrayList<>();
        for (Comment comment : page) {
            parentCommentIds.add(comment.getId());
            commentsMap.put(comment.getId(), new ArrayList<>());
        }
        List<Comment> childComments = commentRepository.findAllByParentIdIn(parentCommentIds);
        childComments.forEach(comment -> {
            List<CommentInfoDto> childList = commentsMap.get(comment.getParentId());
            childList.add(ObjectsMapper.convertToCommentInfoDto(comment));
            commentsMap.put(comment.getParentId(), childList);
        });
        returnList = page.stream()
                .map(c -> new ParentChildCommentDto(c, commentsMap.get(c.getId())))
                .collect(Collectors.toList());
        responseDto.setData(new PageDto(page, returnList));
        responseDto.setSuccess(true);
        responseDto.setStatus(OK.value());
        return responseDto;
    }
}
