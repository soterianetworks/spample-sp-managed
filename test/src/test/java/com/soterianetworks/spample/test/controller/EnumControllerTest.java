package com.soterianetworks.spample.test.controller;

import com.soterianetworks.spample.test.common.SimpleCrudControllerTest;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnumControllerTest extends SimpleCrudControllerTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Override
    public void testList() throws Exception {
        final MvcResult result = mvc.perform(get("/enums")
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();
    }
}
