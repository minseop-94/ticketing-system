package com.example.ticketing.handler.exception;

public class NoSeatsAvailableException extends RuntimeException{
    public NoSeatsAvailableException() {
        super("남은 좌석이 없습니다.");
    }
}
