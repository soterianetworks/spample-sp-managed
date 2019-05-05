package com.soterianetworks.spample.rest.workshop.view;

import com.soterianetworks.spample.domain.model.Workshop;
import com.soterianetworks.spample.rest.common.AbstractBaseView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "UI view for a workshop")
public class WorkshopView extends AbstractBaseView {

    public static WorkshopView from(Workshop model) {
        if (model == null) {
            return null;
        }

        WorkshopView view = new WorkshopView();
        BeanUtils.copyProperties(model, view);
        if (model.getDepartment() != null) {
            view.setDepartmentId(model.getDepartment().getId());
            view.setDepartmentName(model.getDepartment().getName());
        }
        return view;
    }

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String code;

    @ApiModelProperty
    private String description;

    @ApiModelProperty
    private String manager;

    @ApiModelProperty
    private String departmentId;

    @ApiModelProperty
    private String departmentName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
