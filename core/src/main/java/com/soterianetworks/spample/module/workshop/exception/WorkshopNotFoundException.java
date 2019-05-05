package com.soterianetworks.spample.module.workshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkshopNotFoundException extends WorkshopException {

    public WorkshopNotFoundException(WorkshopExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public WorkshopNotFoundException(WorkshopExceptionCode exceptionCode, Object... arguments) {
        super(exceptionCode, arguments);
    }

    public WorkshopNotFoundException(Throwable cause, WorkshopExceptionCode exceptionCode) {
        super(cause, exceptionCode);
    }

    public WorkshopNotFoundException(Throwable cause, WorkshopExceptionCode exceptionCode, Object... arguments) {
        super(cause, exceptionCode, arguments);
    }
}
