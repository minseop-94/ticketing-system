package com.example.ticketing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConcertDTO {
    private Long concertId;
    private String title;
    private String venue;
    private LocalDate concertDate;
    private LocalTime concertTime;
    private int totalSeats;
    private int price;
}
