package com.soterianetworks.spample.module.workshop.exception;


import com.soterianetworks.spase.exception.ExceptionCode;

public enum WorkshopExceptionCode implements ExceptionCode {

    WORKSHOP_NOT_FOUND,

    WORKSHOP_ALREADY_EXISTED,

    WORKSHOP_WORKER_NOT_FOUND,

    WORKSHOP_STAGING_ZONE_NOT_FOUND,

    WORKSHOP_ALREADY_BOUND_BY_STAGING_ZONE,

    WORKSHOP_ALREADY_BOUND_BY_MACHINE,

    WORKSHOP_ALREADY_BOUND_BY_WORKSHOP_WORKER,

    STAGING_ZONE_ALREADY_BOUND_BY_PACK

}
