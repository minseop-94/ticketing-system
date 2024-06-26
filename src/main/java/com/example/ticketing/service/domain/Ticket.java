package com.example.ticketing.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Long ticketId;
    private Long userId;
    private Long concertId;
    private String seatNumber;
    private LocalDateTime ticketingDate;
    private boolean status;
}
