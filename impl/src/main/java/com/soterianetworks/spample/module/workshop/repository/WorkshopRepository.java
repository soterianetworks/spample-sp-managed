package com.soterianetworks.spample.module.workshop.repository;

import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.module.workshop.repository.custom.WorkshopCustomRepository;
import com.soterianetworks.spase.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends AbstractRepository<Workshop>, WorkshopCustomRepository {

    Workshop findByDepartmentIdAndName(String departmentId, String name);

    List<Workshop> findByDepartmentId(String departmentId);

    List<Workshop> findByTenantIdAndBenityIdAndIdNotIn(String tenantId, String benityId, List<String> ids);

    List<Workshop> findByTenantIdAndBenityId(String tenantId, String benityId);

}
