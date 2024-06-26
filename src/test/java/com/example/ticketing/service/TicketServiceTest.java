package com.example.ticketing.service;

import com.example.ticketing.controller.dto.TicketDTO;
import com.example.ticketing.handler.exception.NoSeatsAvailableException;
import com.example.ticketing.handler.exception.NotExistTicketException;
import com.example.ticketing.service.domain.Concert;
import com.example.ticketing.service.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private ConcertRepository concertRepository;

    @Test
    public void testTicketingConcert_Success() {
        // given: 성공적인 티켓팅을 위한 조건 설정
        Long concertId = 1L;
        Long userId = 2L;
        Ticket ticket = Ticket.builder()
                .userId(userId)
                .concertId(concertId)
                .seatNumber("F06").build();
        Concert concert = new Concert();
        concert.setConcertId(concertId);
        concert.setAvailableSeats(10); // 여유 좌석 설정
        when(concertRepository.findConcertById(concertId)).thenReturn(concert);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);


        // when: ticketingConcert 메서드 호출
        TicketDTO ticketDTO = ticketService.ticketingConcert(concertId, userId);

        // then: 결과 검증
        assertNotNull(ticketDTO); // 티켓 DTO가 null이 아닌지 확인
        assertEquals(userId, ticketDTO.getUserId()); // userId 확인
        assertEquals(concertId, ticketDTO.getConcertId()); // concertId 확인
        assertEquals("F06", ticketDTO.getSeatNumber()); // 좌석 번호 확인

//        verify(concertRepository, times(1)).save(concert);
        assertEquals(9, concert.getAvailableSeats()); // 남은 좌석 확인
    }

    @Test
    public void testTicketingConcert_NoSeatsAvailable() {
        // given: 실패 조건 (여유 좌석 없음)
        Long concertId = 1L;
        Long userId = 2L;
        Concert concert = new Concert();
        concert.setConcertId(concertId);
        concert.setAvailableSeats(0); // 여유 좌석 없음
        when(concertRepository.findConcertById(concertId)).thenReturn(concert);

        // when & then: NoSeatsAvailableException 발생 확인
        assertThrows(NoSeatsAvailableException.class, () -> ticketService.ticketingConcert(concertId, userId));
    }



    // 티켓 존재, 상태 True
    @Test
    void isTicketingSuccess_WhenTicketExists_AndStatusIsTrue() {
        // Given
        Long userId = 1L;
        Long concertId = 1L;
        Ticket ticket = new Ticket();
        ticket.setStatus(true);
        when(ticketRepository.findTicketByUserIdConcertId(userId, concertId)).thenReturn(ticket);

        // When
        Boolean result = ticketService.isTicketingSuccess(userId, concertId);

        // Then
        assertTrue(result);
    }

    // 티켓 존재, 상태 False
    @Test
    void isTicketingSuccess_WhenTicketExists_AndStatusIsFalse() {
        // Given
        Long userId = 1L;
        Long concertId = 1L;
        Ticket ticket = new Ticket();
        ticket.setStatus(false);
        when(ticketRepository.findTicketByUserIdConcertId(userId, concertId)).thenReturn(ticket);

        // When
        Boolean result = ticketService.isTicketingSuccess(userId, concertId);

        // Then
        assertFalse(result);
    }

    @Test
    void isTicketingThrowsNotExistTicketException_WhenTicketDoesNotExist() {
        // Given
        Long userId = 1L;
        Long concertId = 1L;
        when(ticketRepository.findTicketByUserIdConcertId(userId, concertId)).thenReturn(null);

        // When & Then
        NotExistTicketException exception = assertThrows(NotExistTicketException.class, () -> {
            ticketService.isTicketingSuccess(userId, concertId);
        });

        // Then
        assertThat(exception.getMessage()).isEqualTo("Ticket does not exist for user " + userId + " and concert " + concertId);
    }
}