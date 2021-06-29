package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.exception.UsuarioDesatualizadoException;
import com.desafio.api.quc.exception.UsuarioNaoExisteException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface AutenticacaoController {

    public ResponseEntity<Usuario> login(@RequestParam("email") final String email, @RequestParam("nome") final String nome) throws UsuarioNaoExisteException, UsuarioDesatualizadoException;
    
}