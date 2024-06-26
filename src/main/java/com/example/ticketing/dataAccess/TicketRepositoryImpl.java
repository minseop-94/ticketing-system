package com.example.ticketing.dataAccess;

import com.example.ticketing.dataAccess.entity.TicketEntity;
import com.example.ticketing.dataAccess.orm.TicketJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository{

    @Autowired
    private TicketJpaRepository ticketJpaRepository;

    @Override
    public Optional<TicketEntity> findTicketByUserIdConcertId(Long userId, Long concertId) {
        return Optional.ofNullable(ticketJpaRepository.findByUserIdAndConcertId(userId, concertId));

    }

    @Override
    public TicketEntity save(TicketEntity ticket) {
        return ticketJpaRepository.save(ticket);
    }
}
