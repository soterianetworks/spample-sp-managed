package com.soterianetworks.spample.rest.context.view;

import com.soterianetworks.spample.rest.common.AbstractBaseView;
import com.soterianetworks.spase.domain.model.Benity;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

public class BenitySimpleView extends AbstractBaseView {

    public static BenitySimpleView from(Benity fromObject) {
        if (fromObject == null) {
            return null;
        }
        BenitySimpleView result = new BenitySimpleView();
        BeanUtils.copyProperties(fromObject, result);
        return result;
    }

    @ApiModelProperty
    private String code;

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String shortName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
