package com.example.ticketing.controller;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /*
     * TODO - (핵심) 콘서트 신청 API POST /concerts/{id}/apply
     * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
     * - 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
     * - 각 강의는 선착순 30명만 신청 가능합니다.
     * - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
     * - 어떤 유저가 특강을 신청했는지 히스토리를 저장해야한다.
     */
    // Question(loso): 콘서트 티켓팅을 하는 기능이 뭘해야 RESTful 해지지? 현재는 계층 구조를 가지지 못함. 
    @PostMapping("/{concertId}/apply")
    public ResponseEntity<TicketDTO> bookConcert(
            @PathVariable Long concertId,
            @RequestBody Long userId
    ) {

//        return ResponseEntity.ok().body(new TicketDTO());
        return ResponseEntity.ok().body(ticketService.ticketingConcert(concertId, userId));
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
