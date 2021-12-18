package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Comment;
import com.qakashilliacea.entity.Publication;
import com.qakashilliacea.entity.User;
import com.qakashilliacea.respository.CommentRepository;
import com.qakashilliacea.respository.PublicationRepository;
import com.qakashilliacea.respository.UserRepository;
import com.qakashilliacea.service.CommentService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.CommentDto;
import com.qakashilliacea.web.dto.CommentInfoDto;
import com.qakashilliacea.web.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

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

        User userObj = userRepository.findByUsername(principal.getName()).get();

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
}
