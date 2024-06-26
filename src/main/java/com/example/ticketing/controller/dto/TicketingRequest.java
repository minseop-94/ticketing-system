package com.example.ticketing.controller.dto;

import lombok.Data;

@Data
public class TicketingRequest {
    private Long userID;
    private Long concertID;
}
