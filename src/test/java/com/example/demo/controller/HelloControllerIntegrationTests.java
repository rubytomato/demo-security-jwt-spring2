package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    final private MediaType contentTypeText = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void greeting() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("hello world"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("hello world");
    }

    @Test
    public void greetingWithMessage() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/hello/{message}", "WORLD")
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("hello WORLD"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("hello WORLD");
    }

    @Test
    public void postGreeting() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.post("/hello")
                .param("message", "WORLD")
                .accept(MediaType.TEXT_PLAIN);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeText))
                .andExpect(content().string("hello WORLD"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("hello WORLD");
    }

}
