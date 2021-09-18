package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.response.view.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class TestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TestController testController;

    @Test
    public void testMethod() throws Exception {

        BDDMockito.given(testController.testMethod()).willReturn("test String");

        mvc.perform(get("/test/testMethod")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("test String"));
    }

    @Test
    void testMethod2() throws Exception {
        BDDMockito.given(testController.testMethod2()).willReturn("Test String 2");

        mvc.perform(get("/test/testMethod2")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Test String 2"));
    }

    @Test
    void getPerson() throws Exception {
        Person person = new Person(1, "Ravi");
        BDDMockito.given(testController.getPerson()).willReturn(person);

        mvc.perform(get("/test/person")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)));
    }
}
