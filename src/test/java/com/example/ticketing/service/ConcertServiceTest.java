package com.example.ticketing.service;

import com.example.ticketing.service.domain.Concert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConcertServiceTest {

    @Mock
    ConcertRepository concertRepository;
    @InjectMocks
    private ConcertService concertService;

    @Test
    void getConcertList() {
        // given
        List<Concert> expectedConcertList = new ArrayList<>();
        expectedConcertList.add(new Concert(1L, "콘서트1", "장소1", LocalDate.now(), LocalTime.now(), 100, 10000, LocalDateTime.now(), LocalDateTime.now()));
        expectedConcertList.add(new Concert(2L, "콘서트2", "장소2", LocalDate.now(), LocalTime.now(), 200, 20000, LocalDateTime.now(), LocalDateTime.now()));
        when(concertRepository.findAll()).thenReturn(expectedConcertList);

        // when
        List<Concert> actualConcertList = concertService.getConcertList();

        // then
        assertThat(actualConcertList).isEqualTo(expectedConcertList);
    }
}