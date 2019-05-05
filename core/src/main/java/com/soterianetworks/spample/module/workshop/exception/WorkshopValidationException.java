package com.soterianetworks.spample.module.workshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WorkshopValidationException extends WorkshopException {

    public WorkshopValidationException(WorkshopExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public WorkshopValidationException(WorkshopExceptionCode exceptionCode, Object... arguments) {
        super(exceptionCode, arguments);
    }

    public WorkshopValidationException(Throwable cause, WorkshopExceptionCode exceptionCode) {
        super(cause, exceptionCode);
    }

    public WorkshopValidationException(Throwable cause, WorkshopExceptionCode exceptionCode, Object... arguments) {
        super(cause, exceptionCode, arguments);
    }
}
