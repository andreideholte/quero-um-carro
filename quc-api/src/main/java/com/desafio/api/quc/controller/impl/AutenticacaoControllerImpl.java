package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.AutenticacaoController;
import com.desafio.api.quc.document.ValidateResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "Autenticacao")
@RequestMapping("/auth")
@RestController
public class AutenticacaoControllerImpl implements AutenticacaoController {

	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public ResponseEntity<ValidateResponse> login(String email, String nome) {
		// TODO Auto-generated method stub
		return null;
	}
	
  }