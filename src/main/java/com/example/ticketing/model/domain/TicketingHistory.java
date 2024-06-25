package com.example.ticketing.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketingHistory {
    private int ticketingId;
    private int userId;
    private int concertId;
    private String seatNumber;
    private LocalDateTime ticketingDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
