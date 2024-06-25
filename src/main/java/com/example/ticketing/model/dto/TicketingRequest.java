package com.example.ticketing.model.dto;

import lombok.Data;

@Data
public class TicketingRequest {
    private Long userID;
    private Long concertID;
}
