package com.example.ticketing.service;

import com.example.ticketing.service.domain.Concert;

import java.util.List;

public interface ConcertRepository {

    Concert findConcertById(Long concertId);
    List<Concert> getALLConcerts = null;

}
