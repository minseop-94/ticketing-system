package com.example.ticketing.service;

import com.example.ticketing.controller.dto.ConcertDTO;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.mapper.ConcertMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// Mockito 사용시, MockitoAnnotations.openMocks(this) 호출해서 Mockito 어노테이션 초기화 필요 
// or @ExtendWith(MockitoExtension.class) 사용 class 레벨
@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock
    ConcertRepository concertRepository;
    @InjectMocks
    private ConcertService concertService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void getConcertList() {
        // given
        List<Concert> concertList = new ArrayList<>();
        concertList.add(new Concert(1L, "콘서트1", "장소1", LocalDate.now(), LocalTime.now(), 100, 10000, LocalDateTime.now(), LocalDateTime.now()));
        concertList.add(new Concert(2L, "콘서트2", "장소2", LocalDate.now(), LocalTime.now(), 200, 20000, LocalDateTime.now(), LocalDateTime.now()));
        when(concertRepository.findAll()).thenReturn(concertList);

        List<ConcertDTO> expectedConcertDTOList = concertList.stream()
            .map(ConcertMapper::toDTO)
            .collect(Collectors.toList());

        // when
        List<ConcertDTO> actualConcertList = concertService.getConcertList();

        // then
        assertThat(actualConcertList).isEqualTo(expectedConcertDTOList);
    }
}