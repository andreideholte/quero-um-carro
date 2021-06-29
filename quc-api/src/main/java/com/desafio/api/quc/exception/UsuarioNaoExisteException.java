package com.desafio.api.quc.exception;

public class UsuarioNaoExisteException extends Exception { 
    public UsuarioNaoExisteException(String errorMessage) {
        super(errorMessage);
    }

    public UsuarioNaoExisteException() {
    }
}