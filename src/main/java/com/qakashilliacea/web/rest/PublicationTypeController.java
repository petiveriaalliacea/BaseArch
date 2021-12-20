package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.PublicationTypeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.qakashilliacea.util.constants.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRIVATE_API_ENDPOINT + "/publication/type")
@ApiModel(value = "PublicationTypeController", description = "Publication type data controller")
public class PublicationTypeController {
    private final PublicationTypeService publicationTypeService;

    @GetMapping("/getPublicationTypeById/{id}")
    @ApiOperation(value = "get publication type info by id")
    public ResponseEntity getPublicationTypeById(@ApiParam(value = "publication type id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationTypeService.getPublicationTypeInfoById(id));
    }
}
