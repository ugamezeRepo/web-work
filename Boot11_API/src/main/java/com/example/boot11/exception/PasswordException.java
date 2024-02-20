package com.example.boot11.exception;

public class PasswordException extends RuntimeException {
    
    // 생성자
    public PasswordException(String message) {
        super(message);
    }
}
