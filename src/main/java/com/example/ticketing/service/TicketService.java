package com.example.ticketing.service;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.dataAccess.ConcertRepository;
import com.example.ticketing.dataAccess.TicketRepository;
import com.example.ticketing.dataAccess.entity.TicketEntity;
import com.example.ticketing.handler.exception.NoSeatsAvailableException;
import com.example.ticketing.handler.exception.NotExistTicketException;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.domain.Ticket;
import com.example.ticketing.service.mapper.ConcertMapper;
import com.example.ticketing.service.mapper.TicketMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private ConcertRepository concertRepository;

    public TicketDTO ticketingConcert(Long concertId, Long userId) {
        // concert 여유 좌석이 있는지 호회
        Concert concert = concertRepository.findConcertById(concertId)
                .map(ConcertMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Concert not found with id: " + concertId));

        // 콘서트 티켓 예매 시간이 되었는지 확인
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime concertStartDateTime = LocalDateTime.of(concert.getConcertDate(), concert.getConcertTime());
        // 콘서트 예매 시작 시간과 현재 시간 비교
        if (currentDateTime.isBefore(concertStartDateTime)) {
            throw new IllegalStateException("티켓 예매 시간이 아직 되지 않았습니다.");
        }

        // 이미 등록한 유저인지 확인
        boolean isAlreadyTicketing = ticketRepository.existsByUserIdAndConcertId(userId, concertId);
        if (isAlreadyTicketing) {
            throw new IllegalStateException("이미 예매한 콘서트입니다.");
        }

        // 여유 좌석이 있다면
        if (concert.getAvailableSeats() > 0) {
            // ticket 정보 저장
            TicketEntity ticket = ticketRepository.save(TicketEntity.builder()
                            .userId(userId)
                            .concertId(concertId)
                            .seatNumber("F06") // 좌석 번호, 테스트 용으로 고정.
                            .status(true).build());
            // 남은 좌석 차감 -1
            concert.setAvailableSeats(concert.getAvailableSeats() - 1);
            // concert 정보 저장
            concertRepository.save(ConcertMapper.toEntity(concert));

            return TicketMapper.toDTO(TicketMapper.toDomain(ticket));

        } else { // 여유 좌석이 없다면 throw exception
            throw new NoSeatsAvailableException();
        }
    }

    // Question: 티켓 조회를 위해 ConcertRepository을 가져 왔는데, 이게 레이어드 계층 구조를 잘 지킨 것인가?
    //      그렇다고 service에서 service를 호출하는 것도 이상하지 않은가?
    //      단일 책임 원칙에서도 벗어 나는게 아닌가? 근데 필요하지 않은가?


    // TODO(loso): JPA Lazy Loading 발생 상황에 대한 정확한 파악 필요.
    public Boolean isTicketingSuccess(Long userId, Long concertId) {
        Ticket ticket = ticketRepository.findTicketByUserIdConcertId(userId, concertId)
                .map(TicketMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Concert not found with id: " + userId + " concertId: " + concertId));

        if (ticket == null) {
            throw new NotExistTicketException("Ticket does not exist for user " + userId + " and concert " + concertId);
        }

        return ticket.isStatus();
    }
}
