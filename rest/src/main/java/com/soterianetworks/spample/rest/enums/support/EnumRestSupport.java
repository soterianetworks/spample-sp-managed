package com.soterianetworks.spample.rest.enums.support;

import java.util.Map;

public interface EnumRestSupport {

    Map<String, Map<String, Map<String, String>>> listEnums() throws ClassNotFoundException;

}
