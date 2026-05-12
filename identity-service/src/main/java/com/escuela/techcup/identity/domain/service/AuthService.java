package com.escuela.techcup.identity.domain.service;

import com.escuela.techcup.identity.adapter.in.dto.LoginRequestDTO;
import com.escuela.techcup.identity.adapter.in.dto.LoginResponseDTO;
import com.escuela.techcup.identity.adapter.in.dto.RegisterRequestDTO;
import com.escuela.techcup.identity.domain.model.Rol;
import com.escuela.techcup.identity.domain.model.Usuario;
import com.escuela.techcup.identity.domain.port.in.AuthUseCase;
import com.escuela.techcup.identity.domain.port.out.UsuarioRepository;
import com.escuela.techcup.identity.infrastructure.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Lógica de negocio de autenticación.
 * No depende de Spring Web ni de JPA directamente — solo de los puertos.
 */
@Service
public class AuthService implements AuthUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!usuario.isActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        String token = jwtUtil.generateToken(usuario);
        return new LoginResponseDTO(token, usuario.getRol().name(), usuario.getNombre());
    }

    @Override
    public void register(RegisterRequestDTO request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        nuevoUsuario.setRol(determinarRol(request.getEmail()));
        nuevoUsuario.setActivo(true);

        usuarioRepository.save(nuevoUsuario);
    }

    /**
     * Determina el rol inicial según el dominio del correo.
     * Correos @escuelaing.edu.co → ESTUDIANTE
     * Correos personales → FAMILIAR
     */
    private Rol determinarRol(String email) {
        if (email.endsWith("@escuelaing.edu.co")) {
            return Rol.ESTUDIANTE;
        }
        return Rol.FAMILIAR;
    }
}
