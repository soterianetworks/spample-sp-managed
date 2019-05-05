package com.soterianetworks.spample.module.workshop.service.impl;

import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.module.audit.AuditAction;
import com.soterianetworks.spample.module.audit.AuditLogger;
import com.soterianetworks.spample.module.workshop.Constants;
import com.soterianetworks.spample.module.workshop.exception.WorkshopExceptionCode;
import com.soterianetworks.spample.module.workshop.exception.WorkshopValidationException;
import com.soterianetworks.spample.module.workshop.repository.WorkshopRepository;
import com.soterianetworks.spample.module.workshop.request.WorkshopSaveRequest;
import com.soterianetworks.spample.module.workshop.request.WorkshopSearchRequest;
import com.soterianetworks.spample.module.workshop.service.WorkshopService;
import com.soterianetworks.spample.module.workshop.validator.WorkshopValidator;
import com.soterianetworks.spase.context.TenantContext;
import com.soterianetworks.spase.domain.model.Department;
import com.soterianetworks.spase.repository.UserRepository;
import com.soterianetworks.spase.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author tundra-dz
 */
@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Resource
    private UserRepository userRepository;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Workshop createWorkshop(WorkshopSaveRequest request) {
        Department department = departmentService.getDepartment(request.getDepartmentId());
        Workshop otherWorkshop = workshopRepository.findByDepartmentIdAndName(request.getDepartmentId(),
                                                                              request.getName());
        if (otherWorkshop != null) {
            AuditLogger.logFail(AuditAction.CREATING,
                                Constants.AUDIT_WORKSHOP,
                                request.getName(),
                                new WorkshopValidationException(WorkshopExceptionCode.WORKSHOP_ALREADY_EXISTED,
                                                                request.getName()));
        }

        Workshop workshopCreating = toModel(request);
        workshopCreating.setDepartment(department);
        Workshop workshopCreated = workshopRepository.save(workshopCreating);
        AuditLogger.logSuccess(AuditAction.CREATED, Constants.AUDIT_WORKSHOP, workshopCreated.getId());

        return workshopCreated;
    }

    @Override
    public Workshop updateWorkshop(String id, WorkshopSaveRequest request) {
        Workshop workshopExists = WorkshopValidator.existsAndReturn(workshopRepository, id, AuditAction.UPDATING);

        Workshop otherWorkshop = workshopRepository.findByDepartmentIdAndName(request.getDepartmentId(),
                                                                              request.getName());
        if (otherWorkshop != null && !workshopExists.equals(otherWorkshop)) {
            AuditLogger.logFail(AuditAction.UPDATING,
                                Constants.AUDIT_WORKSHOP,
                                request.getName(),
                                new WorkshopValidationException(WorkshopExceptionCode.WORKSHOP_ALREADY_EXISTED,
                                                                request.getName()));
        }

        BeanUtils.copyProperties(request, workshopExists);
        if (!workshopExists.getDepartment().getId().equals(request.getDepartmentId())) {
            Department department = departmentService.getDepartment(request.getDepartmentId());
            if (department != null) {
                workshopExists.setDepartment(department);
            }
        }
        Workshop workshopUpdated = workshopRepository.save(workshopExists);
        AuditLogger.logSuccess(AuditAction.UPDATED, Constants.AUDIT_WORKSHOP, workshopUpdated.getId());

        return workshopUpdated;
    }

    @Override
    public void deleteWorkshop(String id) {
        Workshop workshop = WorkshopValidator.existsAndReturn(workshopRepository, id, AuditAction.DELETING);
        //TODO check reference
        workshopRepository.delete(workshop);
        AuditLogger.logSuccess(AuditAction.DELETED, Constants.AUDIT_WORKSHOP, id);
    }

    @Override
    public Workshop getWorkshop(String id) {
        return WorkshopValidator.existsAndReturn(workshopRepository, id, AuditAction.VIEWING);
    }

    @Override
    public Page<Workshop> listWorkshops(WorkshopSearchRequest request, TenantContext tenantContext) {
        return workshopRepository.search(request);
    }

    private Workshop toModel(WorkshopSaveRequest request) {
        Workshop workshop = new Workshop();
        BeanUtils.copyProperties(request, workshop);
        return workshop;
    }

}
