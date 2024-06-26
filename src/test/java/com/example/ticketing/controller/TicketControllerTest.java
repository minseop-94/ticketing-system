package com.example.ticketing.controller;

import com.example.ticketing.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
// controller 클래스를 명시해 줘야지, 다른 클래스들을 Test 시 불러오지 않는구나?
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    TicketService ticketService;

    @Test
    void checkTicketingSuccess() throws Exception {
        // given
        Long userId = 1L;
        Long concertId = 100L;
        Boolean expectedResult = true;

        when(ticketService.isTicketingSuccess(userId, concertId)).thenReturn(true);

        // when then
        mockMvc.perform(get("/tickets/application/{userId}/{concertId}", userId, concertId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expectedResult));
    }
}