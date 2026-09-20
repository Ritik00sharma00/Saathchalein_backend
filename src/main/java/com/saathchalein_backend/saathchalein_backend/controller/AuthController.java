package com.saathchalein_backend.saathchalein_backend.controller;

import com.saathchalein_backend.saathchalein_backend.dbo.UserDBO;
import com.saathchalein_backend.saathchalein_backend.dto.AdminCreateRequest;
import com.saathchalein_backend.saathchalein_backend.dto.AuthResponse;
import com.saathchalein_backend.saathchalein_backend.dto.LoginRequest;
import com.saathchalein_backend.saathchalein_backend.dto.SignupRequest;
import com.saathchalein_backend.saathchalein_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<UserDBO> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/auth/admin-create")
    public ResponseEntity<UserDBO> createAdmin(@Valid @RequestBody AdminCreateRequest request) {
        return ResponseEntity.ok(authService.createAdmin(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
