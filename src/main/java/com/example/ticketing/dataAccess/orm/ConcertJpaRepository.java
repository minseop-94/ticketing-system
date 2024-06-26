package com.example.ticketing.dataAccess.orm;

import com.example.ticketing.dataAccess.entity.ConcertEntity;
import com.example.ticketing.service.domain.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {
}
