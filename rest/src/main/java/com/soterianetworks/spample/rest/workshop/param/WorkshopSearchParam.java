package com.soterianetworks.spample.rest.workshop.param;

import com.soterianetworks.spample.module.workshop.request.WorkshopSearchRequest;
import com.soterianetworks.spase.domain.support.BaseSearchParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Request parameters of searching workshops")
public class WorkshopSearchParam extends BaseSearchParam implements WorkshopSearchRequest {

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String code;

    @ApiModelProperty
    private String manager;

    @ApiModelProperty
    private String departmentId;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
