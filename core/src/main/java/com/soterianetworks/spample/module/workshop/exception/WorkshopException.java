package com.soterianetworks.spample.module.workshop.exception;

import com.soterianetworks.spase.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WorkshopException extends ApplicationException {

    public WorkshopException(WorkshopExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public WorkshopException(WorkshopExceptionCode exceptionCode, Object... arguments) {
        super(exceptionCode, arguments);
    }

    public WorkshopException(Throwable cause, WorkshopExceptionCode exceptionCode) {
        super(cause, exceptionCode);
    }

    public WorkshopException(Throwable cause, WorkshopExceptionCode exceptionCode, Object... arguments) {
        super(cause, exceptionCode, arguments);
    }
}
