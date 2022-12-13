package com.kastourik12.urlshortener.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StatisticsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnUnauthorizedWithNoAuthForGetAll() throws Exception  {
        mockMvc.perform(
                get("/stats/all"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnForbiddenWithUserAuthForGetAll() throws Exception {
        mockMvc.perform(
                        get("/stats/all")
                                .with(jwt().authorities(
                                        new SimpleGrantedAuthority("SCOPE_ROLE_USER")
                                ))
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }
    @Test
    void shouldReturn200WithAdminAuthForGetAll() throws Exception{
        mockMvc.perform(
                    get("/stats/all")
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("SCOPE_ROLE_ADMIN")
                        )))
                .andDo(print())
                .andExpect(status().isOk());
    }



    @Test
    void shouldReturn200ForUrlVisitsWithAuthAdmin() throws Exception{
        mockMvc.perform(
                        get("/stats/1")
                                .with(jwt().authorities(
                                        new SimpleGrantedAuthority("SCOPE_ROLE_ADMIN")
                                )))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnUnauthorizedForUrlVisitsWithNoAuth() throws Exception{
        mockMvc.perform(get("/stats/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    void shouldReturnForbiddenForUrlVisitsWithNoAuth() throws Exception{
        mockMvc.perform(
                        get("/stats/1")
                                .with(jwt().authorities(
                                        new SimpleGrantedAuthority("SCOPE_ROLE_USER")
                                )))
                .andDo(print())
                .andExpect(status().isForbidden());

    }

}