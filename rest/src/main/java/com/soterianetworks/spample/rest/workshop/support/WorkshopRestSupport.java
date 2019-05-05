package com.soterianetworks.spample.rest.workshop.support;

import com.soterianetworks.spample.rest.workshop.param.WorkshopSaveParam;
import com.soterianetworks.spample.rest.workshop.param.WorkshopSearchParam;
import com.soterianetworks.spample.rest.workshop.view.WorkshopView;
import org.springframework.data.domain.Page;

public interface WorkshopRestSupport {

    Page<WorkshopView> listWorkshops(WorkshopSearchParam searchParam);

    WorkshopView getWorkshop(String id);

    WorkshopView createWorkshop(WorkshopSaveParam workshopSaveParam);

    WorkshopView updateWorkshop(String id, WorkshopSaveParam workshopSaveParam);

    void deleteWorkshop(String id);

}
