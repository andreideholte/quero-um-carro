package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.exception.BusinessException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReservaController {
    
    ResponseEntity<Reserva> criar(@RequestBody final Reserva reserva) throws BusinessException;

}