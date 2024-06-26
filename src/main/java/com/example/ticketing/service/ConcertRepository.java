package com.example.ticketing.service;

import com.example.ticketing.service.domain.Concert;

import java.util.List;

// Question(loso): Repository Interface의 계층이 business 인가요? 현재 패키지 구조에서 serivce아래에 있는게 맞을까요?

public interface ConcertRepository {

    Concert findConcertById(Long concertId);
    List<Concert> findAll();
}
