package com.soterianetworks.spample.module.workshop.repository.custom;

import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.module.workshop.request.WorkshopSearchRequest;
import org.springframework.data.domain.Page;

public interface WorkshopCustomRepository {

    Page<Workshop> search(WorkshopSearchRequest searchRequest);

}
