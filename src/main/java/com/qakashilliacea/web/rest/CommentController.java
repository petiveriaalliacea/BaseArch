package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.CommentService;
import com.qakashilliacea.util.constants.Constants;
import com.qakashilliacea.web.dto.CommentDto;
import com.qakashilliacea.web.dto.PageableDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

import static com.qakashilliacea.util.constants.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRIVATE_API_ENDPOINT + "/comments")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/create")
    @ApiOperation(value = "create comment")
    public ResponseEntity createComment(@ApiIgnore @Autowired Principal principal,
                                        @ApiParam("dto to create comment") @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.create(commentDto,principal));
    }

    @PostMapping("/getAllByPublicationId/{publicationId}")
    @ApiOperation(value = "get all comments by publication id")
    public ResponseEntity getAllByPublicationId(@ApiParam(value = "publication id") @PathVariable("publicationId") Long publicationId,
                                                @ApiParam(value = "PageRequestDto") @RequestBody PageableDto dto) {
        return ResponseEntity.ok(commentService.findAllByPublicationId(publicationId, dto));
    }



}
