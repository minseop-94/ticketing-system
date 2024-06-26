package com.example.ticketing.service.mapper;

import com.example.ticketing.controller.dto.ConcertDTO;
import com.example.ticketing.dataAccess.entity.ConcertEntity;
import com.example.ticketing.service.domain.Concert;

public class ConcertMapper {
    // Question(loso): dto, domain, entity 간의 구분이 잘 되지 않는다. 각 꼐층에서 필요한 데이터가 무엇이 있을지 고민
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

    public static ConcertEntity toEntity(Concert concert) {
        return ConcertEntity.builder()
            .concertId(concert.getConcertId())
            .title(concert.getTitle())
            .venue(concert.getVenue())
            .concertDate(concert.getConcertDate())
            .concertTime(concert.getConcertTime())
            .totalSeats(concert.getTotalSeats())
            .price(concert.getPrice())
            .build();
    }

    public static Concert toDomain(ConcertEntity concertEntity) {
        return Concert.builder()
            .concertId(concertEntity.getConcertId())
            .title(concertEntity.getTitle())
            .venue(concertEntity.getVenue())
            .concertDate(concertEntity.getConcertDate())
            .concertTime(concertEntity.getConcertTime())
            .totalSeats(concertEntity.getTotalSeats())
            .price(concertEntity.getPrice())
            .build();
    }
}
