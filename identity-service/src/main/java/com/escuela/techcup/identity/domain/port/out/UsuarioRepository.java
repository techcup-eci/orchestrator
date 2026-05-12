package com.escuela.techcup.identity.domain.port.out;

import com.escuela.techcup.identity.domain.model.Usuario;
import java.util.Optional;

/**
 * Puerto de salida: lo que el dominio NECESITA del exterior (base de datos).
 * El dominio define esta interfaz; la infraestructura la implementa.
 */
public interface UsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Usuario save(Usuario usuario);
}
