package com.example.ticketing.service;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.domain.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketDTO ticketingConcert(Long concertId, Long userId) {
        // concert 여유 좌석이 있는지 호회

        // 여유 좌석이 있다면

        // 여유 좌석이 없다면 throw exception

//        return TicketMapper.toDTO(new Ticket());
        return new TicketDTO();

    }

    public Boolean isTicketingSuccess(Long userId, Long concertId) {
        Ticket ticket = ticketRepository.findTicketByUserIdConcertId(userId, concertId);

        return ticket.isStatus();
    }
}
