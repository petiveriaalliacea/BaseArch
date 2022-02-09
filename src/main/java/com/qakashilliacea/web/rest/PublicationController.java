package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.PublicationService;
import com.qakashilliacea.web.dto.PublicationDto;
import com.qakashilliacea.web.dto.PublicationSortDto;
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
@RequestMapping(PRIVATE_API_ENDPOINT + "/publications")
@ApiModel(value = "PublicationController", description = "Publications data controller")
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "get publication info by id")
    public ResponseEntity getById(@ApiParam(value = "publication id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @PostMapping("/sorted/")
    @ApiOperation(value = "get publication info")
    public ResponseEntity getSorted(@ApiParam(value = "Sorting") @RequestBody PublicationSortDto dto) {
        return ResponseEntity.ok(publicationService.getAllSorted(dto));
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
