package com.example.ticketing.service;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.domain.Ticket;
import com.example.ticketing.service.mapper.TicketMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConcertService {
    private ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getConcertList() {
        // 그냥 조회 concert 있는거 다 List로 반환

        return new ArrayList<Concert>();
    }
}
