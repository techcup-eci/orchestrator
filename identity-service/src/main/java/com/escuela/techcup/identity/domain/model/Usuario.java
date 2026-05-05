package com.escuela.techcup.identity.domain.model;

/**
 * Modelo de dominio del usuario.
 * Esta clase NO tiene anotaciones de JPA — es dominio puro.
 */
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String passwordHash;
    private Rol rol;
    private boolean activo;

    public Usuario() {}

    public Usuario(Long id, String nombre, String email, String passwordHash, Rol rol, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
