package com.kastourik12.urlshortener.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShortUrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldConvertToShortUrl() throws Exception {


        ShortUrlCreationRequest requestBody = new ShortUrlCreationRequest("www.google.com");


        mockMvc.perform(
                    post("/re/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectToJson(requestBody)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("shortUrl")));
    }

    @Test
    public void shouldNotConvertInvalidUrl() throws Exception {


        ShortUrlCreationRequest request = new ShortUrlCreationRequest("invalid url");

        mockMvc.perform(
                    post("/re/create")
                    .contentType(MediaType.APPLICATION_JSON)
                            .content(objectToJson(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Url should be valid")));

    }



    @Test
    void getAndRedirect() {
    }

    private String objectToJson(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}