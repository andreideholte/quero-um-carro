package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.ValidateResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface AutenticacaoController {

    public ResponseEntity<ValidateResponse> login(@RequestParam("email") final String email, @RequestParam("nome") final String nome);
    
}