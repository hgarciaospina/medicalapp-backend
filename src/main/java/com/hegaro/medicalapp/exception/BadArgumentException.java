package com.hegaro.medicalapp.exception;
public class BadArgumentException extends RuntimeException{
    public BadArgumentException(String message) {
        super(message);
    }
}