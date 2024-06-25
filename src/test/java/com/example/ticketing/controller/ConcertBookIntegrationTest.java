package com.example.ticketing.controller;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConcertBookIntegrationTest {

    // 1. 동일한 신청자는 한 번의 수강 신청만 성공 확인.
    // 2. 각 강의는 선착순 30명만 신청 가능 확인.
    // 3, 신청자가 30명이 초과되면 이후 신청자는 요청 실패 확인.
    // 4. 어떤 유저가 특강을 신청했는지 히스토리 저장 확인.
}
