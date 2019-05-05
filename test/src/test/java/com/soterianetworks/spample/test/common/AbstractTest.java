package com.soterianetworks.spample.test.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soterianetworks.spample.test.TestWmsApplication;
import com.soterianetworks.spase.context.SpRequestInterceptor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestWmsApplication.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public abstract class AbstractTest {

    @Autowired
    protected MockMvc mvc;

    protected HttpHeaders headers;

    protected String actorUserName = "jmadmin";

    protected String tenantCode = "WMS";

    protected String benityCode = "GF";

    public void setUp() {
        headers = new HttpHeaders();
        headers.add(SpRequestInterceptor.ACTOR_USERNAME, this.actorUserName);
        headers.add(SpRequestInterceptor.TENANT_CODE, this.tenantCode);
        headers.add(SpRequestInterceptor.BENITY_CODE, this.benityCode);
        headers.add("Accept-Language", "zh-CN");
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(json, clazz);
    }

    protected <T> List<T> mapListFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz));
    }

}
