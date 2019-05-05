package com.soterianetworks.spample.module.workshop.validator;


import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.module.audit.AuditAction;
import com.soterianetworks.spample.module.audit.AuditLogger;
import com.soterianetworks.spample.module.workshop.Constants;
import com.soterianetworks.spample.module.workshop.exception.WorkshopExceptionCode;
import com.soterianetworks.spample.module.workshop.exception.WorkshopNotFoundException;
import com.soterianetworks.spample.module.workshop.repository.WorkshopRepository;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

public class WorkshopValidator {

    public static Workshop existsAndReturn(@NotNull WorkshopRepository repository,
                                           @NotNull String id,
                                           String auditAction) {
        Workshop bean = repository.findOne(id);
        if (bean == null) {
            if (StringUtils.isEmpty(auditAction)) {
                auditAction = AuditAction.VIEWING;
            }
            AuditLogger.logFail(auditAction, Constants.AUDIT_WORKSHOP, id);
            throw new WorkshopNotFoundException(WorkshopExceptionCode.WORKSHOP_NOT_FOUND, id);
        }
        return bean;
    }

    public static void notExistsAndThrow(@NotNull WorkshopRepository repository,
                                         @NotNull String id,
                                         String auditAction) {
        boolean exists = repository.exists(id);
        if (!exists) {
            if (StringUtils.isEmpty(auditAction)) {
                auditAction = AuditAction.VIEWING;
            }
            AuditLogger.logFail(auditAction, Constants.AUDIT_WORKSHOP, id);
            throw new WorkshopNotFoundException(WorkshopExceptionCode.WORKSHOP_NOT_FOUND, id);
        }
    }

}
