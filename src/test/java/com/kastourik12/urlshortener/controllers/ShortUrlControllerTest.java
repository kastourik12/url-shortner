package com.kastourik12.urlshortener.controllers;

import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.kastourik12.urlshortener.utils.ObjectToJson.objectToJson;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShortUrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test @Order(3)
    void shouldConvertToShortUrl() throws Exception {


        ShortUrlCreationRequest requestBody = new ShortUrlCreationRequest("www.google.com");


        mockMvc.perform(
                    post("/re/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectToJson(requestBody)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("visitedTimes")));
    }

    @Test @Order(2)
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


    @Test @Order(1)
    void shouldResponse404ForNoSavedUrl() throws Exception{
        mockMvc.perform(
                get("/re/b"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("no url found for this short")));
    }

    @Test @Order(4)
    public void shouldReturnRedirectUrl() throws Exception {
        mockMvc.perform(
                    get("/re/b"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }


    @Test @Order(5)
    public void shouldReturn400ForEmptyBody() throws Exception{
        ShortUrlCreationRequest request = new ShortUrlCreationRequest();
        mockMvc.perform(
                    post("/re/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("url should not be empty")));
    }


}