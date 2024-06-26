package com.example.ticketing.handler.exception;

public class NotExistTicketException extends RuntimeException  {
    public NotExistTicketException(String message) {
        super(message);
    }
}
