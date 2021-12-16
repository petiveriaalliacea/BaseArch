package com.qakashilliacea.web.rest.auth;

import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.web.dto.LoginDto;
import com.qakashilliacea.web.dto.RegisterDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
@ApiModel(value = "AuthController", description = "Authentication Controller , checks user exists or not.")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    @ApiOperation(value = "Registration process")
    public ResponseEntity signUp(@ApiParam(value = "dto with register data") @RequestBody RegisterDto dto) {
        return ResponseEntity.ok(authService.signUp(dto));
    }

    @PostMapping("/signIn")
    @ApiOperation(value = "login process")
    public ResponseEntity signIn(@ApiParam(value = "dto with login data") @RequestBody LoginDto dto) {
        return ResponseEntity.ok(authService.signIn(dto));
    }
}
