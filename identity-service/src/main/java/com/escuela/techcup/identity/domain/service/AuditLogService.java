package com.escuela.techcup.identity.domain.service;

import com.escuela.techcup.identity.adapter.out.persistence.AuditLogEntity;
import com.escuela.techcup.identity.domain.port.out.AuditLogRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio para registrar acciones de auditoría.
 * Cada acción importante (login, registro, logout) queda registrada.
 */
@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void registrar(String accion, String usuarioEmail) {
        AuditLogEntity log = new AuditLogEntity();
        log.setAccion(accion);
        log.setUsuarioEmail(usuarioEmail);
        auditLogRepository.save(log);
    }
}
