package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Comment;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.qakashilliacea.util.constants.ErrorConstants.BAD_REQUEST;
import static java.util.Objects.isNull;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseDto create(CommentDto commentDto, Principal principal) {
        ResponseDto responseDto = new ResponseDto<CommentInfoDto>();

        if(isNull(commentDto.getDescription())){
            responseDto.setStatus(BAD_REQUEST);
            responseDto.setErrorMessage(ErrorMessages.requiredFieldIsEmpty("description"));
            return responseDto;
        }

        Comment comment = ObjectsMapper.convertCommentCreatorToComment(commentDto);

        commentRepository.save(comment);
        responseDto.setSuccess(true);
        responseDto.setData(ObjectsMapper.convertToCommentDto(comment));
        return responseDto;
    }

    @Override
    public ResponseDto findAllByPublicationId(Long publicationId, PageableDto dto) {
        ResponseDto responseDto = new ResponseDto();
        Page<Comment> page = (Page<Comment>) commentRepository.findAllById(publicationId, PageUtils.buildPageable(dto));

        List<CommentInfoDto> dtoList = page
                .stream()
                .map(ObjectsMapper::convertToCommentDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .success(true)
                .data(new PageDto(page, dtoList))
                .build();
    }
}
