package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.PublicationService;
import com.qakashilliacea.web.dto.PageableDto;
import com.qakashilliacea.web.dto.PublicationDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/publications")
@ApiModel(value = "PublicationController", description = "Publications data controller")
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "get publication info by id")
    public ResponseEntity getById(@ApiParam(value = "publication id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @PostMapping("/getByUserId/{userId}")
    @ApiOperation(value = "get publication info by user Id")
    public ResponseEntity getByUserId(@ApiParam(value = "user id") @PathVariable("userId") Long userId,
                                      @ApiParam(value = "PageRequestDto") @RequestBody PageableDto dto) {
        return ResponseEntity.ok(publicationService.getAllByUserId(userId, dto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete publication by id")
    public ResponseEntity deleteById(@ApiParam(value = "publication id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationService.deleteById(id));
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "get all publications")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(publicationService.findAll());
    }


    @PostMapping("/create")
    @ApiOperation(value = "create publication")
    public ResponseEntity createPublication(@ApiIgnore @Autowired Principal principal,
                                            @ApiParam(value = "dto to create publication") @RequestBody PublicationDto publicationDto) {
        return ResponseEntity.ok(publicationService.create(publicationDto, principal));
    }
}
