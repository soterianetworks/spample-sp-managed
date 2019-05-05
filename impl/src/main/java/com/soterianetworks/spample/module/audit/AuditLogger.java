package com.soterianetworks.spample.module.audit;

import com.soterianetworks.spase.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogger.class);

    public static void logSuccess(final String action,
                                  final String type,
                                  final String recipient) {
        LOGGER.info(new Audit(action, type, recipient, "successfully").getMessage());
    }

    public static void logFail(final String action,
                               final String type,
                               final String recipient) {
        LOGGER.error(new Audit(action, type, recipient, "failed").getMessage());
    }

    public static void logFail(final String action,
                               final String type,
                               final String recipient,
                               final ApplicationException ex) {
        LOGGER.error(new Audit(action, type, recipient, "failed").getMessage(), ex);
        if (ex != null) {
            throw ex;
        }
    }
}
