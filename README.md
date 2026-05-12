# TechCup ALPINE — Gateway & Identity Service

Repositorio del equipo **ALPINE** para el proyecto TechCup Fútbol.

## Estructura del proyecto

```
techcup-alpine/
├── gateway/              # Orquestador / API Gateway (puerto 8080)
│   └── src/main/java/com/escuela/techcup/gateway/
│       ├── filter/       # AuthFilter + AuthFilterFactory (validación JWT)
│       ├── config/       # CorsConfig
│       ├── exception/    # GlobalErrorHandler
│       └── util/         # JwtUtil (solo valida tokens)
│
└── identity-service/     # Servicio de Identidad (puerto 8081)
    └── src/main/java/com/escuela/techcup/identity/
        ├── controller/   # AuthController (endpoints REST)
        ├── adapter/
        │   ├── in/dto/   # RegisterRequestDTO, LoginRequestDTO, LoginResponseDTO
        │   └── out/persistence/ # Entidades JPA + repositorios JPA
        ├── domain/
        │   ├── model/    # Usuario, Rol (dominio puro, sin JPA)
        │   ├── port/in/  # AuthUseCase (interfaz)
        │   ├── port/out/ # UsuarioRepository, AuditLogRepository (interfaces)
        │   └── service/  # AuthService, AuditLogService (lógica de negocio)
        └── infrastructure/
            ├── config/   # SecurityConfig
            └── util/     # JwtUtil (genera tokens)
```




