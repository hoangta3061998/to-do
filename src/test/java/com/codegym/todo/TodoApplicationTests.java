package com.codegym.todo;


import com.codegym.todo.model.Work;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class TodoApplicationTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getWorksList() throws Exception {
        String uri = "/list";
        MvcResult result = mvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        mvc.perform(get(uri)).andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(6)));
    }

    @Test
    public void createWork() throws Exception {
        String uri = "/create";
        Work work = new Work();
        work.setName("Giat do");
        work.setStartingDate(new Date());
        work.setEndingDate(new Date());
        work.setStatus("Done");
        String inputJson = super.mapToJson(work);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteWork() throws Exception {
        String uri = "/delete?id=5";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    public void updateWork() throws Exception {
        String uri = "/edit?id=6";
        Work work = new Work();
        work.setName("Mua cf");
        String inputJson = super.mapToJson(work);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }
}