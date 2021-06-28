package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.ReservaController;
import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.service.ReservaService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Reserva")
@RestController
public class ReservaControllerImpl implements ReservaController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
	private ReservaService reservaService;

    @Override
    @ApiOperation(value = "Criar reserva de um veiculo para um usuario")
    @PostMapping(value = "/reserva", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) throws BusinessException {
        LOGGER.info("[RESERVA-CONTROLLER-IMPL][Criar] Iniciando Processo de criar uma reserva. Reserva [{}]", reserva);

        Reserva reservaCriada = reservaService.criar(reserva);

        LOGGER.info("[RESERVA-CONTROLLER-IMPL][Criar] Finalizado Processo de criar uma reserva. Reserva Criada [{}]", reservaCriada);

        return new ResponseEntity<>(reservaCriada, HttpStatus.CREATED);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> validacaoViolada(WebRequest webRequest, Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> erroInterno(WebRequest webRequest, Exception exception) {
        LOGGER.error("[RESERVA-CONTROLLER-IMPL][Criar] Erro na criacao da reserva.");
        LOGGER.error(exception);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
