package com.example.demo.controller;

import com.example.demo.entity.Memo;
import com.example.demo.entity.User;
import com.example.demo.security.SimpleLoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemoControllerIntegrationTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    final private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getOne() throws Exception {
        Memo expected = new Memo(1L, "memo shopping", "memo1 description", false, LocalDateTime.of(2018, 1, 4, 12, 1, 0));
        String expectedJson = objectMapper.writeValueAsString(expected);

        User user = new User(1L, "aaaa", "pass", "aaa.aaa@example.com", true);
        SimpleLoginUser loginUser = new SimpleLoginUser(user);

        RequestBuilder builder = MockMvcRequestBuilders.get("/memo/{id}", expected.getId())
                .with(user(loginUser))
                //.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJra2FtaW11cmFAZXhhbXBsZS5jb20iLCJpYXQiOjE1MjM0NjQ5NzcsImV4cCI6MTUyMzQ2NTU3NywiWC1BVVRIT1JJVElFUyI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiWC1VU0VSTkFNRSI6ImthbWltdXJhIn0.qMVKdGd8c1UIYryR1y7WOosdh6k-jsVGxc3XrUU2Fg8")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.title").value(expected.getTitle()))
                .andExpect(jsonPath("$.description").value(expected.getDescription()))
                .andExpect(jsonPath("$.done").value(expected.getDone()))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);
    }

}
