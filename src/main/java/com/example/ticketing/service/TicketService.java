package com.example.ticketing.service;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.dataAccess.ConcertRepository;
import com.example.ticketing.dataAccess.TicketRepository;
import com.example.ticketing.handler.exception.NoSeatsAvailableException;
import com.example.ticketing.handler.exception.NotExistTicketException;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.domain.Ticket;
import com.example.ticketing.service.mapper.TicketMapper;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private ConcertRepository concertRepository;

    public TicketDTO ticketingConcert(Long concertId, Long userId) {
        // concert 여유 좌석이 있는지 호회
        Concert concert = concertRepository.findConcertById(concertId);

        // 여유 좌석이 있다면
        if (concert.getAvailableSeats() > 0) {
            // ticket 정보 저장
            Ticket ticket = ticketRepository.save(Ticket.builder()
                            .userId(userId)
                            .concertId(concertId)
                            .seatNumber("F06") // 좌석 번호, 테스트 용으로 고정.
                            .status(true).build());
            // 남은 좌석 차감 -1
            concert.setAvailableSeats(concert.getAvailableSeats() - 1);
            // concert 정보 저장
            concertRepository.save(concert);

            return TicketMapper.toDTO(ticket);

        } else { // 여유 좌석이 없다면 throw exception
            throw new NoSeatsAvailableException();
        }
    }

    // Question: 티켓 조회를 위해 ConcertRepository을 가져 왔는데, 이게 레이어드 계층 구조를 잘 지킨 것인가?
    //      그렇다고 service에서 service를 호출하는 것도 이상하지 않은가?
    //      단일 책임 원칙에서도 벗어 나는게 아닌가? 근데 필요하지 않은가?


    // TODO(loso): JPA Lazy Loading 발생 상황에 대한 정확한 파악 필요.
    public Boolean isTicketingSuccess(Long userId, Long concertId) {
        Ticket ticket = ticketRepository.findTicketByUserIdConcertId(userId, concertId);

        if (ticket == null) {
            throw new NotExistTicketException("Ticket does not exist for user " + userId + " and concert " + concertId);
        }

        return ticket.isStatus();
    }
}
