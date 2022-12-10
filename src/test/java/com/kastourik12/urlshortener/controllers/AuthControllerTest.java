package com.kastourik12.urlshortener.controllers;

import com.kastourik12.urlshortener.payloads.request.SignUpRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test @Order(2)
    public void shouldSignUp() throws Exception{

        SignUpRequest request = new SignUpRequest("admin","admin");

        mockMvc.perform(
                    post("/auth/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectToJson(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User registered successfully ")));

    }

    @Test @Order(3)
    public void shouldNotSignWithAlreadyRegisteredUsername() throws Exception{
        SignUpRequest request = new SignUpRequest("admin","admin");
        mockMvc.perform(
                        post("/auth/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectToJson(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("username already exists")));

    }
}
