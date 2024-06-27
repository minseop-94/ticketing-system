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

    // Question(loso): 콘서트 티켓팅을 하는 기능이 뭘해야 RESTful 해지지? 현재는 계층 구조를 가지지 못함.
    @PostMapping("/{concertId}/apply")
    public ResponseEntity<TicketDTO> bookConcert(
            @PathVariable Long concertId,
            @RequestBody Long userId
    ) {

//        return ResponseEntity.ok().body(new TicketDTO());
        return ResponseEntity.ok().body(ticketService.ticketingConcert(concertId, userId));
    }


    // Question(loso): 이게, ConcertController에 있는게 맞나? -> Ticket이라는 객체를 통해서, 어떤 user가 어떤 concert에 ticket을 가지고 있는지 여부가 Ticket에 있는데, 여긴 ConcertController 잖아?


//  Question(loso): 두 엔드포인트 중 좀더 RESTful한 것은 무엇일까요? /users/{userId}/concerts/{concertId} VS /application/{userId}/{concertId} :
    @GetMapping("/application/{userId}/{concertId}")
    public ResponseEntity<Boolean> checkTicketingSuccess (
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "concertId") Long concertId
    ) {
        return ResponseEntity.ok().body(ticketService.isTicketingSuccess(userId, concertId));
    }
}
