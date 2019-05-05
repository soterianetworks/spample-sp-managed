package com.soterianetworks.spample.module.workshop.request;

import com.soterianetworks.spample.domain.request.NamingSaveRequest;

/**
 * The request to save workshop
 */
public interface WorkshopSaveRequest extends NamingSaveRequest {

    String getDepartmentId();

    String getCode();
}
