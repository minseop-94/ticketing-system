package com.example.ticketing.service.mapper;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.domain.Ticket;

public class TicketMapper {
    public static TicketDTO toDTO(Ticket ticket) {
                return TicketDTO.builder()
                .ticketId(ticket.getTicketId())
                .userId(ticket.getUserId())
                .concertId(ticket.getConcertId())
                .seatNumber(ticket.getSeatNumber())
                .ticketingDate(ticket.getTicketingDate())
                .status(ticket.isStatus())
                .build();
    }
}
