package com.example.ticketing.service;

import com.example.ticketing.service.domain.Ticket;

public interface TicketRepository {
    Ticket findTicketByUserIdConcertId(Long userId, Long concertId);

    Ticket save(Ticket ticket);
}
