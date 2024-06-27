//package com.example.ticketing.integration;
//
//import com.example.ticketing.infra.ConcertRepository;
//import com.example.ticketing.infra.TicketRepository;
//import com.example.ticketing.service.domain.Concert;
//import com.example.ticketing.service.domain.Ticket;
//import com.example.ticketing.service.mapper.ConcertMapper;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional // 테스트 후 데이터 롤백
//public class TicketingIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ConcertRepository concertRepository;
//
//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @Test
//    public void testBookConcert_Success() throws Exception {
//        // given: 콘서트 생성 및 저장 (availableSeats > 0)
//        Concert concert = Concert.builder()
//                .title("Test Concert")
//                .venue("Test Venue")
//                .concertDate(LocalDate.now().plusDays(1)) // 내일
//                .concertTime(LocalTime.of(19, 0))
//                .totalSeats(100)
//                .availableSeats(50)
//                .price(50000)
//                .build();
//        concertRepository.save(ConcertMapper.toEntity(concert));
//
//        // when: 티켓 예매 API 호출A
//        mockMvc.perform(post("/tickets/{concertId}/apply", concert.getConcertId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("1")) // userId
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.ticketId").exists())
//                .andExpect(jsonPath("$.concertId").value(concert.getConcertId()))
//                .andExpect(jsonPath("$.userId").value(1))
//                .andExpect(jsonPath("$.seatNumber").value("F06"))
//                .andExpect(jsonPath("$.status").value(true));
//
//        // then: 티켓 생성 및 좌석 감소 확인
//        Ticket ticket = ticketRepository.findByUserIdAndConcertId(1L, concert.getConcertId()).orElse(null);
//        assertNotNull(ticket);
//        assertEquals(49, concertRepository.findById(concert.getConcertId()).get().getAvailableSeats());
//    }
//
//    // 추가 테스트 케이스 (예매 시간 이전, 이미 예매, 매진 등)
//    // ...
//
//}
