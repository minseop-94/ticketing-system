package com.example.ticketing.dataAccess;

import com.example.ticketing.dataAccess.entity.ConcertEntity;

import java.util.List;
import java.util.Optional;

// Question(loso): Repository Interface의 계층이 business 인가요? 현재 패키지 구조에서 serivce아래에 있는게 맞을까요?

public interface ConcertRepository {

    Optional<ConcertEntity> findConcertById(Long concertId);
    List<ConcertEntity> findAll();
    ConcertEntity save(ConcertEntity concert);
}
