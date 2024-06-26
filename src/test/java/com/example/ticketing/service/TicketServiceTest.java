package com.example.ticketing.service;

import com.example.ticketing.handler.exception.NotExistTicketException;
import com.example.ticketing.service.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

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