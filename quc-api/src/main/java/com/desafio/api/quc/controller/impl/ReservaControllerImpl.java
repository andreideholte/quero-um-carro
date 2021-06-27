package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.ReservaController;
import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.document.ReservasCollection;
import com.desafio.api.quc.service.ReservaService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "Reserva")
@RequestMapping("/reserva")
@RestController
public class ReservaControllerImpl implements ReservaController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
	private ReservaService reservaService;

    @Override
    public ResponseEntity<Reserva> criar(Reserva reserva) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ReservasCollection> buscarPorUsuario(String idUsuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ReservasCollection> buscarPorVeiculo(String idVeiculo) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
