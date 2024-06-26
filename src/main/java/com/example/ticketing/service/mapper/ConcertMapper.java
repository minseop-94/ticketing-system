package com.example.ticketing.service.mapper;

import com.example.ticketing.controller.dto.ConcertDTO;
import com.example.ticketing.service.domain.Concert;

public class ConcertMapper {
        public static ConcertDTO toDTO(Concert concert) {
        return ConcertDTO.builder()
                .concertId(concert.getConcertId())
                .title(concert.getTitle())
                .venue(concert.getVenue())
                .concertDate(concert.getConcertDate())
                .concertTime(concert.getConcertTime())
                .totalSeats(concert.getTotalSeats())
                .price(concert.getPrice())
                .build();
    }
}
