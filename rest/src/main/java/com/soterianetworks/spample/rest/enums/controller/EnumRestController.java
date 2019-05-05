package com.soterianetworks.spample.rest.enums.controller;

import com.soterianetworks.spample.rest.enums.support.EnumRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "/enums", description = "REST end-points to manage enums.")
@RestController
@RequestMapping("/enums")
public class EnumRestController {

    @Autowired
    private EnumRestSupport enumRestSupport;

    @ApiOperation(value = "Retrieve enums.", httpMethod = "GET", response = Map.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Map<String, Map<String, String>>> listEnums()
            throws ClassNotFoundException {
        return enumRestSupport.listEnums();
    }

}
