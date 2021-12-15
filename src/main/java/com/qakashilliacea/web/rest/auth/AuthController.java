package com.qakashilliacea.web.rest.auth;

import com.qakashilliacea.service.AuthService;
import com.qakashilliacea.util.Constants;
import com.qakashilliacea.web.dto.LoginDto;
import com.qakashilliacea.web.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public ResponseEntity signIn(@Autowired Principal principal, @RequestBody LoginDto dto) {
        System.out.println(principal.getName());
        return ResponseEntity.ok(authService.signIn(dto));
    }
}
