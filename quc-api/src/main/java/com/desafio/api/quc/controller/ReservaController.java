package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.document.ReservasCollection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReservaController {
    
    ResponseEntity<Reserva> criar(@RequestBody final Reserva reserva);

    ResponseEntity<ReservasCollection> buscarPorUsuario(@RequestParam("usuario") final String idUsuario);

    ResponseEntity<ReservasCollection> buscarPorVeiculo(@RequestParam("veiculo") final String idVeiculo);

}