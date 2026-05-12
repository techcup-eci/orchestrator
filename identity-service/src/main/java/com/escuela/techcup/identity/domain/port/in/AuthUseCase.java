package com.escuela.techcup.identity.domain.port.in;

import com.escuela.techcup.identity.adapter.in.dto.LoginRequestDTO;
import com.escuela.techcup.identity.adapter.in.dto.LoginResponseDTO;
import com.escuela.techcup.identity.adapter.in.dto.RegisterRequestDTO;

/**
 * Puerto de entrada: define LO QUE el dominio puede hacer.
 * El controlador solo conoce esta interfaz, no la implementación.
 */
public interface AuthUseCase {
    LoginResponseDTO login(LoginRequestDTO request);
    void register(RegisterRequestDTO request);
}
