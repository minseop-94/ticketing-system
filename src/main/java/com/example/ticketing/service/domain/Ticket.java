package com.example.ticketing.service.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticket {
    private Long ticketId;
    private Long userId;
    private Long concertId;
    private String seatNumber;
    private LocalDateTime ticketingDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
