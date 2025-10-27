package com.findash.account_service.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.findash.account_service.dto.RegisterRequest;

import com.findash.account_service.service.AuthService;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.registerUser(request.name(), request.email(), request.password());
    }
}
