package com.escuela.techcup.identity.controller;

import com.escuela.techcup.identity.adapter.in.dto.LoginRequestDTO;
import com.escuela.techcup.identity.adapter.in.dto.LoginResponseDTO;
import com.escuela.techcup.identity.adapter.in.dto.RegisterRequestDTO;
import com.escuela.techcup.identity.domain.port.in.AuthUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authUseCase.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO request) {
        authUseCase.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
