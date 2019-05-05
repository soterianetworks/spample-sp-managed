package com.soterianetworks.spample.test.common;

import com.soterianetworks.spase.domain.model.StringIdentity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public abstract class SimpleCrudControllerTest extends AbstractTest {

    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testCrud() throws Exception {
        try {
            this.testCreate();
            this.testUpdate();
            this.testList();
            this.testGetDetail();
            this.testNotFound();
            this.testCustom();
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        } finally {
            //clean up
            this.testDelete();
        }
    }

    protected void testCustom() throws Exception {

    }

    protected void testDelete() throws Exception {

    }

    protected void testNotFound() throws Exception {

    }

    protected void testGetDetail() throws Exception {

    }

    protected void testList() throws Exception {

    }

    protected void testCreate() throws Exception {

    }

    protected void testUpdate() throws Exception {

    }

    protected <T extends StringIdentity> String doCreateEntityTest(String url, Object t, Class<T> clazz)
            throws Exception {
        return doCreateEntityTest(url, t, clazz, null);
    }

    protected <T extends StringIdentity> String doCreateEntityTest(String url, Object t, Class<T> clazz,
                                                                   Consumer<T> consumer)
            throws Exception {
        final String inputJson = super.mapToJson(t);

        final MvcResult result = mvc.perform(post(url)
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .content(inputJson)
                                                     .headers(this.headers))
                                    .andExpect(status().isCreated())
                                    .andExpect(jsonPath("$.id", notNullValue()))
                                    .andReturn();

        T created = super.mapFromJson(result.getResponse().getContentAsString(), clazz);

        if (consumer != null) {
            consumer.accept(created);
        }

        return created.getId();
    }

    protected <T extends StringIdentity> String doCreateEntityTest(String url,
                                                                   Object[] uriVars,
                                                                   Object t,
                                                                   Class<T> clazz)
            throws Exception {
        return doCreateEntityTest(url, uriVars, t, clazz, null);
    }

    protected <T extends StringIdentity> String doCreateEntityTest(String url,
                                                                   Object[] uriVars,
                                                                   Object t,
                                                                   Class<T> clazz,
                                                                   Consumer<T> consumer)
            throws Exception {
        final String inputJson = super.mapToJson(t);

        final MvcResult result = mvc.perform(post(url, uriVars)
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .content(inputJson)
                                                     .headers(this.headers))
                                    .andExpect(status().isCreated())
                                    .andExpect(jsonPath("$.id", notNullValue()))
                                    .andReturn();

        T created = super.mapFromJson(result.getResponse().getContentAsString(), clazz);

        if (consumer != null) {
            consumer.accept(created);
        }

        return created.getId();
    }

    protected <T extends StringIdentity> List<String> doBatchCreateEntityTest(String url,
                                                                              Object[] uriVars,
                                                                              Object t,
                                                                              Class<T> clazz)
            throws Exception {
        return doBatchCreateEntityTest(url, uriVars, t, clazz, null);
    }

    protected <T extends StringIdentity> List<String> doBatchCreateEntityTest(String url,
                                                                              Object[] uriVars,
                                                                              Object t,
                                                                              Class<T> clazz,
                                                                              Consumer<List<T>> consumer)
            throws Exception {
        final String inputJson = super.mapToJson(t);

        final MvcResult result = mvc.perform(post(url, uriVars)
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .content(inputJson)
                                                     .headers(this.headers))
                                    .andExpect(status().isCreated())
//
                                    .andReturn();

        List<T> created = super.mapListFromJson(result.getResponse().getContentAsString(), clazz);

        if (consumer != null) {
            consumer.accept(created);
        }

        return created.stream().map(StringIdentity::getId).collect(Collectors.toList());
    }

    protected <T extends StringIdentity> String doUpdateEntityTest(String url,
                                                                   Object[] uriVars,
                                                                   Object t,
                                                                   Class<T> clazz)
            throws Exception {
        return doUpdateEntityTest(url, uriVars, t, clazz, null);
    }

    protected <T extends StringIdentity> String doUpdateEntityTest(String url,
                                                                   Object[] uriVars,
                                                                   Object t,
                                                                   Class<T> clazz,
                                                                   Consumer<T> consumer)
            throws Exception {
        final String inputJson = super.mapToJson(t);

        final MvcResult result = mvc.perform(put(url, uriVars)
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .content(inputJson)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        if (clazz == null) {
            return null;
        }

        T created = super.mapFromJson(result.getResponse().getContentAsString(), clazz);

        Assert.assertNotNull(created);

        if (consumer != null) {
            consumer.accept(created);
        }

        return created.getId();
    }

    protected <T extends StringIdentity> List<String> doBatchUpdateEntityTest(String url,
                                                                              Object[] uriVars,
                                                                              Object t,
                                                                              Class<T> clazz)
            throws Exception {
        return doBatchUpdateEntityTest(url, uriVars, t, clazz, null);
    }

    protected <T extends StringIdentity> List<String> doBatchUpdateEntityTest(String url,
                                                                              Object[] uriVars,
                                                                              Object t,
                                                                              Class<T> clazz,
                                                                              Consumer<List<T>> consumer)
            throws Exception {
        final String inputJson = super.mapToJson(t);

        final MvcResult result = mvc.perform(put(url, uriVars)
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .content(inputJson)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        if (clazz == null) {
            return null;
        }

        List<T> updated = super.mapListFromJson(result.getResponse().getContentAsString(), clazz);

        if (consumer != null) {
            consumer.accept(updated);
        }

        return updated.stream().map(StringIdentity::getId).collect(Collectors.toList());
    }

    protected <T extends StringIdentity> void doGetEntityListTest(String url,
                                                                  Class<T> clazz) throws Exception {
        doGetEntityListTest(url, clazz, null);
    }

    protected <T extends StringIdentity> void doGetEntityListTest(String url,
                                                                  Class<T> clazz,
                                                                  Consumer<List<T>> consumer) throws Exception {
        final MvcResult result = mvc.perform(get(url)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        List<T> views = super.mapListFromJson(result.getResponse().getContentAsString(),
                                              clazz);
        Assert.assertNotNull(views);

        if (consumer != null) {
            consumer.accept(views);
        }
    }

    protected <T extends StringIdentity> void doGetEntityListTest(String url,
                                                                  Object[] uriVars,
                                                                  Class<T> clazz) throws Exception {
        doGetEntityListTest(url, uriVars, clazz, null);
    }

    protected <T extends StringIdentity> void doGetEntityListTest(String url,
                                                                  Object[] uriVars,
                                                                  Class<T> clazz,
                                                                  Consumer<List<T>> consumer) throws Exception {
        final MvcResult result = mvc.perform(get(url, uriVars)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andReturn();

        List<T> views = super.mapListFromJson(result.getResponse().getContentAsString(), clazz);

        Assert.assertNotNull(views);

        if (consumer != null) {
            consumer.accept(views);
        }
    }

    protected <T extends StringIdentity> void doGetEntityDetailTest(String url, Object[] uriVars, Class<T> clazz)
            throws Exception {
        doGetEntityDetailTest(url, uriVars, clazz, null);
    }

    protected <T extends StringIdentity> void doGetEntityDetailTest(String url,
                                                                    Object[] uriVars,
                                                                    Class<T> clazz,
                                                                    Consumer<T> consumer)
            throws Exception {
        final MvcResult result = mvc.perform(get(url, uriVars)
                                                     .accept(MediaType.APPLICATION_JSON)
                                                     .headers(this.headers))
                                    .andExpect(status().isOk())
                                    .andExpect(jsonPath("$.id", notNullValue()))
                                    .andReturn();

        T view = super.mapFromJson(result.getResponse().getContentAsString(), clazz);

        Assert.assertNotNull(view);

        if (consumer != null) {
            consumer.accept(view);
        }
    }

    protected void doEntityNotFoundTest(String url, Object... uriVars) throws Exception {
        mvc.perform(get(url, uriVars)
                            .accept(MediaType.APPLICATION_JSON)
                            .headers(this.headers))
           .andExpect(status().isNotFound())
           .andReturn();
    }

    protected void doDeleteEntityTest(String url, Object[] uriVars) throws Exception {
        this.doDeleteEntityTest(url, uriVars, null);

    }

    protected void doDeleteEntityTest(String url, Object[] uriVars, Consumer<MockHttpServletResponse> consumer)
            throws Exception {
        doDeleteEntityTest(url, uriVars, null, consumer);
    }

    protected void doDeleteEntityTest(String url, Object[] uriVars, Object param) throws Exception {
        doDeleteEntityTest(url, uriVars, param, null);


    }

    protected void doDeleteEntityTest(String url, Object[] uriVars, Object param,
                                      Consumer<MockHttpServletResponse> consumer) throws Exception {
        if (uriVars == null) {
            return;
        }
        MockHttpServletRequestBuilder requestBuilder = delete(url, uriVars)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(this.headers);
        if (param != null) {
            requestBuilder.content(super.mapToJson(param));
        }
        final MvcResult result = mvc.perform(requestBuilder)
                                    .andExpect(status().isNoContent())
                                    .andReturn();

        if (consumer != null) {
            consumer.accept(result.getResponse());
        }

    }


}
