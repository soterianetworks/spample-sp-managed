package com.soterianetworks.spample.module.workshop.request;

import com.soterianetworks.spase.domain.request.BenitySearchRequest;
import com.soterianetworks.spase.domain.request.PageableSearchRequest;
import com.soterianetworks.spase.domain.request.QuickSearchRequest;
import com.soterianetworks.spase.domain.request.TenantSearchRequest;

public interface WorkshopSearchRequest extends PageableSearchRequest, QuickSearchRequest, TenantSearchRequest, BenitySearchRequest {

    String getName();

    String getDepartmentId();

    String getCode();

    String getManager();

}
