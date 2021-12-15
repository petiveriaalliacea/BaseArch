package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.UserService;
import com.qakashilliacea.web.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
@ApiModel(value = "UserController", description = "User data controller")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "Create user")
    public ResponseEntity createUser(@ApiParam(value = "Dto with user data") @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by id")
    public ResponseEntity updateUser(@ApiParam(value = "Dto with user data") @RequestBody UserDto dto,
                                     @ApiParam(value = "User id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updateUser(dto, id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Read user info by id")
    public ResponseEntity readUser(@ApiParam(value = "User id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.readUser(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    public ResponseEntity deleteUser(@ApiParam(value = "UserId") @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
