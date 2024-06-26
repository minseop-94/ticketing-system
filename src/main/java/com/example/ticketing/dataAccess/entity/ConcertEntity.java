package com.example.ticketing.dataAccess.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "concert")
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 활성화
public class ConcertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String venue;

    @Column(nullable = false)
    private LocalDate concertDate;

    @Column(nullable = false)
    private LocalTime concertTime;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false)
    private int availableSeats;

    @Column(nullable = false)
    private int price;

    @CreatedDate // 생성 시 자동으로 현재 시간 설정
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 시 자동으로 현재 시간 설정
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
