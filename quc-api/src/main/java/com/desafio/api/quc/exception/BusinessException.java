package com.desafio.api.quc.exception;

public class BusinessException extends Exception { 
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}