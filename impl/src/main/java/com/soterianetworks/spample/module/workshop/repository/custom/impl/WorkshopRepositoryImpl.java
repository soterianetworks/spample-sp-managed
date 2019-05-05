package com.soterianetworks.spample.module.workshop.repository.custom.impl;

import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.module.workshop.repository.custom.WorkshopCustomRepository;
import com.soterianetworks.spample.module.workshop.request.WorkshopSearchRequest;
import com.soterianetworks.spase.repository.custom.AbstractCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public class WorkshopRepositoryImpl extends AbstractCustomRepository implements WorkshopCustomRepository {

    @Override
    public Page<Workshop> search(WorkshopSearchRequest searchRequest) {

        //TODO
        return null;
    }

}
