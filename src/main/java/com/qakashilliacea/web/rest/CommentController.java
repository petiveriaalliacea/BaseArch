package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.CommentService;
import com.qakashilliacea.util.constants.Constants;
import com.qakashilliacea.web.dto.CommentDto;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

import static com.qakashilliacea.util.constants.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRIVATE_API_ENDPOINT + "/comments")
public class CommentController {
    private final CommentService commentService;


    @PostMapping
    public void createComment(@ApiIgnore @Autowired Principal principal,
                              @ApiParam("dto to create comment") @RequestBody CommentDto commentDto){


    }

}
