package com.example.ticketing.service.mapper;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.dataAccess.entity.TicketEntity;
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

    public static Ticket toDomain(TicketEntity ticket) {
                return Ticket.builder()
                .ticketId(ticket.getTicketId())
                .userId(ticket.getUserId())
                .concertId(ticket.getConcertId())
                .seatNumber(ticket.getSeatNumber())
                .ticketingDate(ticket.getTicketingDate())
                .status(ticket.isStatus())
                .build();
    }

    public static TicketEntity toEntity(Ticket ticket) {
                return TicketEntity.builder()
                .ticketId(ticket.getTicketId())
                .userId(ticket.getUserId())
                .concertId(ticket.getConcertId())
                .seatNumber(ticket.getSeatNumber())
                .ticketingDate(ticket.getTicketingDate())
                .status(ticket.isStatus())
                .build();
    }


}
