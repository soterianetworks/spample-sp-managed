package com.soterianetworks.spample.module.workshop.service;

import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.module.workshop.request.WorkshopSaveRequest;
import com.soterianetworks.spample.module.workshop.request.WorkshopSearchRequest;
import com.soterianetworks.spase.context.TenantContext;
import org.springframework.data.domain.Page;

public interface WorkshopService {

    Workshop createWorkshop(WorkshopSaveRequest request);

    Workshop updateWorkshop(String id, WorkshopSaveRequest request);

    void deleteWorkshop(String id);

    Workshop getWorkshop(String id);

    Page<Workshop> listWorkshops(WorkshopSearchRequest request, TenantContext tenantContext);

}
