package com.example.ticketing.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Concert {
    private Long concertId;
    private String title;
    private String venue;
    private LocalDate concertDate;
    private LocalTime concertTime;
    private int totalSeats;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
