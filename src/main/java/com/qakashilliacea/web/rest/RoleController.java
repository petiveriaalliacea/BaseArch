package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.RoleService;
import io.swagger.annotations.ApiModel;
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
    public ResponseEntity getById (@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }
    @GetMapping
    public ResponseEntity getAll () {
        return ResponseEntity.ok(roleService.getAll());
    }
}
