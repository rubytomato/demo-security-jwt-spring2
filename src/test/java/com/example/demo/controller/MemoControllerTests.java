package com.example.demo.controller;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MemoController.class)
public class MemoControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemoService memoService;

    final private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @WithMockUser
    @Test
    public void getOne() throws Exception {
        Memo expected = new Memo(1L, "test title", "test description", false, LocalDateTime.of(2018, 3, 19, 0, 34, 49));
        String expectedJson = objectMapper.writeValueAsString(expected);
        Mockito.when(memoService.findById(anyLong())).thenReturn(Optional.ofNullable(expected));

        RequestBuilder builder = MockMvcRequestBuilders.get("/memo/{id}", 1L)
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
