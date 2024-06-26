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

    /*
    *  TODO - (기본) 콘서트 목록 API GET /concerts
    * - 단 한번의 특강을 위한 것이 아닌 날짜별로 특강이 존재할 수 있는 범용적인 서비스로 변화시켜 봅니다.
    * - 이를 수용하기 위해, 특강 엔티티의 경우 기본 과제 SPEC 을 만족하는 설계에서 변경되어야 할 수 있습니다.
    * - 수강신청 API 요청 및 응답 또한 이를 잘 수용할 수 있는 구조로 변경되어야 할 것입니다.
    * - 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
    * - 추가로 정원이 특강마다 다르다면 어떻게 처리할것인가..? 고민해 보셔라~
    */
    @GetMapping()
    public ResponseEntity<List<ConcertDTO>> getConcertList() {


        return ResponseEntity.ok().body(concertService.getConcertList());
    }

}
