package com.qakashilliacea.service;

import com.qakashilliacea.web.dto.CommentDto;
import com.qakashilliacea.web.dto.ResponseDto;

import java.security.Principal;

public interface CommentService {

    ResponseDto create(CommentDto commentDto, Principal principal);

    //return all/find all pub_id


}
