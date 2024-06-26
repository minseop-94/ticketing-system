package com.example.ticketing.dataAccess;

import com.example.ticketing.dataAccess.entity.TicketEntity;

import java.util.Optional;

public interface TicketRepository {
    Optional<TicketEntity> findTicketByUserIdConcertId(Long userId, Long concertId);

    TicketEntity save(TicketEntity ticket);
}
