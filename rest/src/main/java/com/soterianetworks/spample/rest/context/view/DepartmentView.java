package com.soterianetworks.spample.rest.context.view;

import com.soterianetworks.spample.rest.common.AbstractBaseView;
import com.soterianetworks.spase.domain.model.Department;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "UI view for department config.")
public class DepartmentView extends AbstractBaseView {

    public static DepartmentView from(Department model) {
        if (model == null) {
            return null;
        }
        DepartmentView view = new DepartmentView();
        BeanUtils.copyProperties(model, view);
        if (model.getBenity() != null) {
            view.setBenityId(model.getBenity().getId());
        }
        return view;
    }

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String code;

    @ApiModelProperty
    private String benityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBenityId() {
        return benityId;
    }

    public void setBenityId(String benityId) {
        this.benityId = benityId;
    }

}
