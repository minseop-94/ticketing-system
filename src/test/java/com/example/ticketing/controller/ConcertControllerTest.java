package com.example.ticketing.controller;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.ConcertService;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.domain.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest
class ConcertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ConcertService concertService;

    @Test
    void bookConcert() throws Exception {
        // given
        TicketDTO expectedTicket = new TicketDTO();
        Long userId = 1L;
        Long concertId = 10L;

        when(concertService.ticketingConcert(concertId, userId)).thenReturn(expectedTicket);

        // when
        MvcResult result = mockMvc.perform(post("/concerts/{concertId}/apply", concertId)
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
    void getConcertList() throws Exception {
        // given
        List<Concert> expectedConcertList = List.of(new Concert());

        // when
        MvcResult result = mockMvc.perform(get("/concerts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Concert> actualConcerts = objectMapper.readValue(responseBody, new TypeReference<List<Concert>>() {});

        assertThat(actualConcerts).isEqualTo(expectedConcertList);
    }


//    @Test
//    void checkTicketingSuccess() throws Exception {
//        // given
//        Long userId = 1L;
//        Boolean expectedResult = true;
//
//        // when then
//        mockMvc.perform(get("/concerts/application/{userId}", userId)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(expectedResult));
//    }
}