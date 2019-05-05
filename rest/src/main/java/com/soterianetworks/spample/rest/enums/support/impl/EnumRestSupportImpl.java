package com.soterianetworks.spample.rest.enums.support.impl;

import com.soterianetworks.spample.rest.enums.support.EnumRestSupport;
import com.soterianetworks.spase.domain.enums.i18n.I18nEnumRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EnumRestSupportImpl implements EnumRestSupport {

    @Autowired
    private I18nEnumRegistry enumRegistry;

    @Override
    public Map<String, Map<String, Map<String, String>>> listEnums() {
        return enumRegistry.getEnums();
    }
}
