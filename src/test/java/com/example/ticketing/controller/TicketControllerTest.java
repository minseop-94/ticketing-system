package com.example.ticketing.controller;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void bookConcert() throws Exception {
        // given
        TicketDTO expectedTicket = new TicketDTO();
        Long userId = 1L;
        Long concertId = 10L;

        when(ticketService.ticketingConcert(concertId, userId)).thenReturn(expectedTicket);

        // when
        MvcResult result = mockMvc.perform(post("/tickets/{concertId}/apply", concertId)
                        .contentType(MediaType.APPLICATION_JSON) // Content-Type 헤더 추가
                        .content(String.valueOf(userId))) // 요청 본문 추가
                .andExpect(status().isOk()) // stauts 확인
                .andReturn();

        // then
        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        TicketDTO actualTicket = objectMapper.readValue(responseBody, TicketDTO.class);

        assertThat(actualTicket).isEqualTo(expectedTicket);
    }


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