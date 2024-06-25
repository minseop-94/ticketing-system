package com.example.ticketing.model.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
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
