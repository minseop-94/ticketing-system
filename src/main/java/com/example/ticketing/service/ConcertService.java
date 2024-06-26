package com.example.ticketing.service;

import com.example.ticketing.controller.dto.ConcertDTO;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.mapper.ConcertMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertService {
    private ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<ConcertDTO> getConcertList() {
        // 그냥 조회 concert 있는거 다 List로 반환

        List<Concert> concertList = concertRepository.findAll();

        return concertList.stream()
                .map(ConcertMapper::toDTO)
                .collect(Collectors.toList());
    }
}
