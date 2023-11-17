package com.task.crudApp.controller;

import com.task.crudApp.model.LoginRequest;
import com.task.crudApp.model.LoginResponse;
import com.task.crudApp.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest)
    {
        var token = jwtIssuer.issue(1L, loginRequest.getEmail(), List.of("USER"));
        return LoginResponse.builder().accessToken(token).build();
    }
}
