package com.example.ticketing.controller;

import com.example.ticketing.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    /*
    * TODO - (기본) 특강 신청 완료 여부 조회 API GET /tickets/application/{userId}
    * - 특정 userId 로 특강 신청 완료 여부를 조회하는 API 를 작성합니다.
    * - 특강 신청에 성공한 사용자는 성공했음을, 특강 등록자 명단에 없는 사용자는 실패했음을 반환합니다. (true, false)
    * */

    // Question(loso): 이게, ConcertController에 있는게 맞나? -> Ticket이라는 객체를 통해서, 어떤 user가 어떤 concert에 ticket을 가지고 있는지 여부가 Ticket에 있는데, 여긴 ConcertController 잖아?


//  Question(loso): 두 엔드포인트 중 좀더 RESTful한 것은 무엇일까요? /users/{userId}/concerts/{concertId} VS /application/{userId}/{concertId} :
    @GetMapping("/application/{userId}/{concertId}")
    public ResponseEntity<Boolean> checkTicketingSuccess (
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "concertId") Long concertId
    ) {
        // Question(loso): 예약 여부를 확인하는
        return ResponseEntity.ok().body(ticketService.isTicketingSuccess(userId, concertId));
    }
}
