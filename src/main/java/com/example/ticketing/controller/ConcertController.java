package com.example.ticketing.controller;

import com.example.ticketing.controller.dto.ConcertDTO;
import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.domain.Ticket;
import com.example.ticketing.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    private ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping()
    public ResponseEntity<List<ConcertDTO>> getConcertList() {


        return ResponseEntity.ok().body(concertService.getConcertList());
    }

}
