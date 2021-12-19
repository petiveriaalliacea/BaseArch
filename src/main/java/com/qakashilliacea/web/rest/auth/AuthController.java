package com.qakashilliacea.web.rest.auth;

import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.util.constants.Constants;
import com.qakashilliacea.web.dto.LoginDto;
import com.qakashilliacea.web.dto.RegisterDto;
import com.qakashilliacea.web.dto.UserDetailedInfoDto;
import com.qakashilliacea.web.dto.UserRegistrationInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.qakashilliacea.util.constants.Constants.PUBLIC_API_ENDPOINT;

@RestController
@RequestMapping(PUBLIC_API_ENDPOINT + "/auth")
@RequiredArgsConstructor
@ApiModel(value = "AuthController", description = "Authentication Controller , user registration and login processes")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    @ApiOperation(value = "Registration process")
    public ResponseEntity signUp(@ApiParam(value = "dto with register data") @RequestBody UserRegistrationInfoDto dto) {
        return ResponseEntity.ok(authService.signUp(dto));
    }

    @PostMapping("/signIn")
    @ApiOperation(value = "Login process")
    public ResponseEntity signIn(@ApiParam(value = "dto with login data") @RequestBody LoginDto dto) {
        return ResponseEntity.ok(authService.signIn(dto));
    }

    @PostMapping("/verifyEmailAddress")
    @ApiOperation(value = "verification process")
    public ResponseEntity verify(@ApiParam(value = "uuid to verify")
                                 @RequestParam("uuid") String uuid,
                                 @ApiParam(value = "code to verify email")
                                 @RequestParam("code") String code) {
        return ResponseEntity.ok(authService.verifyEmail(uuid, code));
    }
}
