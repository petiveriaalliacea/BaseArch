package com.qakashilliacea.web.rest.auth;

import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.web.dto.LoginDto;
import com.qakashilliacea.web.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody RegisterDto dto) {
        return ResponseEntity.ok(authService.signUp(dto));
    }

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(authService.signIn(dto));
    }
}
