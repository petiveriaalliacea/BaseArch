package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.PublicationService;
import com.qakashilliacea.web.dto.PublicationCreatorDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/publications")
@ApiModel(value = "Publication Controller", description = "Publications data controller")
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "get publication info by id")
    public ResponseEntity getById(@ApiParam(value = "publication id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @GetMapping("/getByUserId/{userId}")
    @ApiOperation(value = "get publication info by user Id")
    public ResponseEntity getByUserId(@ApiParam(value = "user id") @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(publicationService.getByUserId(userId));
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

    @GetMapping("/getByPublicationDate")
    @ApiOperation(value = "get by Publication Date")
    public ResponseEntity getByDate(@ApiParam(value = "publication created date")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                    @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(publicationService.findByPublicationDate(date));
    }

    @PostMapping("/create")
    @ApiOperation(value = "create publication")
    public ResponseEntity createPublication(@ApiIgnore @Autowired Principal principal,
                                            @ApiParam(value = "dto to create publication") @RequestBody PublicationCreatorDto
                                                    publicationCreatorDto) {
        return ResponseEntity.ok(publicationService.create(publicationCreatorDto, principal));
    }
}
