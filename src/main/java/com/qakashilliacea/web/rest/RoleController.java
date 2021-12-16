package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.RoleService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/public/role")
@ApiModel(value = "RoleController", description = "Role retrieve data controller")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/{id}")
    @ApiOperation(value = "read role info by role id")
    public ResponseEntity getById(@ApiParam(value = "role id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping
    @ApiOperation(value = "get all roles")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }
}
