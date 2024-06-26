package com.example.ticketing.service;

import com.example.ticketing.service.domain.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public Boolean isTicketingSuccess(Long userId, Long concertId) {
        Ticket ticket = ticketRepository.findTicketByUserIdConcertId(userId, concertId);

        return ticket.isStatus();
    }
}
