package com.soterianetworks.spample.rest.workshop.support.impl;

import com.soterianetworks.spample.module.workshop.service.WorkshopService;
import com.soterianetworks.spample.rest.workshop.param.WorkshopSaveParam;
import com.soterianetworks.spample.rest.workshop.param.WorkshopSearchParam;
import com.soterianetworks.spample.rest.workshop.support.WorkshopRestSupport;
import com.soterianetworks.spample.rest.workshop.view.WorkshopView;
import com.soterianetworks.spase.context.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class WorkshopRestSupportImpl implements WorkshopRestSupport {

    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private TenantContext tenantContext;

    @Override
    public Page<WorkshopView> listWorkshops(WorkshopSearchParam searchParam) {
        searchParam.setTenantId(tenantContext.getTenantId());
        searchParam.setBenityId(tenantContext.getBenityId());
        return workshopService.listWorkshops(searchParam, tenantContext).map(WorkshopView::from);
    }

    @Override
    public WorkshopView getWorkshop(String id) {
        return WorkshopView.from(workshopService.getWorkshop(id));
    }

    @Override
    public WorkshopView createWorkshop(WorkshopSaveParam workshopSaveParam) {
        return WorkshopView.from(workshopService.createWorkshop(workshopSaveParam));
    }

    @Override
    public WorkshopView updateWorkshop(String id, WorkshopSaveParam workshopSaveParam) {
        return WorkshopView.from(workshopService.updateWorkshop(id, workshopSaveParam));
    }

    @Override
    public void deleteWorkshop(String id) {
        workshopService.deleteWorkshop(id);
    }

}
