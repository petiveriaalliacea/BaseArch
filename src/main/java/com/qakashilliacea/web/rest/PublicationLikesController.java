package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.PublicationLikesService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;

import java.security.Principal;

import static com.qakashilliacea.util.constants.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRIVATE_API_ENDPOINT + "/publication/likes")
@ApiModel(value = "PublicationLikesController", description = "publication like(data) controller")
public class PublicationLikesController {
    private final PublicationLikesService likesService;

    @PostMapping("/addLike/{publicationId}")
    @ApiOperation(value = "add like to publication")
    public ResponseEntity addLike(@ApiParam("publicationId") @PathVariable("publicationId") Long publicationId,
                                  @ApiIgnore @Autowired Principal principal) {
        return ResponseEntity.ok(likesService.addLikeToPublication(publicationId, principal.getName()));
    }

    @Transactional
    @DeleteMapping("/deleteLike/{publicationId}")
    @ApiOperation(value = "delete like from publication")
    public ResponseEntity deleteLike(@ApiParam("publicationId") @PathVariable("publicationId") Long publicationId,
                                     @ApiIgnore @Autowired Principal principal) {
        return ResponseEntity.ok(likesService.deleteLikeFromPublication(publicationId, principal.getName()));
    }
}
