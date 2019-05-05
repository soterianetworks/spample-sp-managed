package com.soterianetworks.spample.rest.workshop.param;

import com.soterianetworks.spample.module.workshop.request.WorkshopSaveRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Request parameters for create or update a workshop")
public class WorkshopSaveParam implements WorkshopSaveRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "{workshop.name.not_null}")
    private String name;

    @ApiModelProperty(required = true)
    @NotNull(message = "{workshop.code.not_null}")
    private String code;

    @ApiModelProperty
    private String manager;

    @ApiModelProperty
    private String description;

    @ApiModelProperty(required = true)
    @NotNull(message = "{workshop.department_extension.not_null}")
    private String departmentId;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
