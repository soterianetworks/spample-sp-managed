package com.soterianetworks.spample.rest.context.view;

import com.soterianetworks.spample.rest.common.AbstractBaseView;
import com.soterianetworks.spase.domain.model.Department;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

public class DepartmentSimpleView extends AbstractBaseView {

    public static DepartmentSimpleView from(Department fromObject) {
        if (fromObject == null) {
            return null;
        }
        DepartmentSimpleView result = new DepartmentSimpleView();
        BeanUtils.copyProperties(fromObject, result);
        if (fromObject.getBenity() != null) {
            result.setBenityId(fromObject.getBenity().getId());
        }
        return result;
    }

    @ApiModelProperty
    private String code;

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String extensionId;

    @ApiModelProperty
    private String benityId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(String extensionId) {
        this.extensionId = extensionId;
    }

    public String getBenityId() {
        return benityId;
    }

    public void setBenityId(String benityId) {
        this.benityId = benityId;
    }
}
