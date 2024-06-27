package com.example.ticketing.dataAccess.orm;

import com.example.ticketing.dataAccess.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {
    TicketEntity findByUserIdAndConcertId(Long userId, Long concertId);

    boolean existsByUserIdAndConcertId(Long userId, Long concertId);

}
