package com.example.boot10.exception;

public class PasswordException extends RuntimeException {
    
    // 생성자
    public PasswordException(String message) {
        super(message);
    }
}
