package com.desafio.api.quc.exception;

public class UsuarioDesatualizadoException extends Exception { 
    public UsuarioDesatualizadoException(String errorMessage) {
        super(errorMessage);
    }

    public UsuarioDesatualizadoException() {
    }
}