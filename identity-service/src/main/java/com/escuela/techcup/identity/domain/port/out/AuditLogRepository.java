package com.escuela.techcup.identity.domain.port.out;

import com.escuela.techcup.identity.adapter.out.persistence.AuditLogEntity;

/**
 * Puerto de salida para persistir logs de auditoría.
 */
public interface AuditLogRepository {
    void save(AuditLogEntity log);
}
