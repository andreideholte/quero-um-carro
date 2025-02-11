package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.AutenticacaoController;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.exception.UsuarioDesatualizadoException;
import com.desafio.api.quc.exception.UsuarioNaoExisteException;
import com.desafio.api.quc.service.AutenticacaoService;
import com.desafio.api.quc.service.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Autenticacao")
@CrossOrigin(origins = "http://localhost:23000")
@RestController
public class AutenticacaoControllerImpl implements AutenticacaoController {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@ApiOperation(value = "Autenticacao")
	@GetMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> login(@RequestParam("email") String email, @RequestParam("nome") String nome)
			throws UsuarioNaoExisteException, UsuarioDesatualizadoException {
		LOGGER.info("[AUTENTICACAO-CONTROLLER-IMPL][Login] Iniciando Processo de login.");

		Usuario usuarioAutenticado = autenticacaoService.autenticar(email, nome);

		LOGGER.info("[AUTENTICACAO-CONTROLLER-IMPL][Login] Finalizado Processo de login.");

		return new ResponseEntity<>(usuarioAutenticado, HttpStatus.OK);
	}

	@ExceptionHandler(UsuarioNaoExisteException.class)
	public ResponseEntity<Usuario> usuarioNaoExiste(WebRequest webRequest, Exception exception) {
		Usuario usuario = new Usuario(null, webRequest.getParameter("email"), webRequest.getParameter("nome"));

		usuario = usuarioService.criar(usuario);

		LOGGER.info("[AUTENTICACAO-CONTROLLER-IMPL][Login] Finalizado Processo de login.");

		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}

	@ExceptionHandler(UsuarioDesatualizadoException.class)
	public ResponseEntity<Usuario> usuarioDesatualizado(WebRequest webRequest, Exception exception) {
		Usuario usuario = usuarioService.buscarPorEmail(webRequest.getParameter("email"));
		usuario.setNome(webRequest.getParameter("nome"));

		usuario = usuarioService.atualizar(usuario);

		LOGGER.info("[AUTENTICACAO-CONTROLLER-IMPL][Login] Finalizado Processo de login.");

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> erroInterno(WebRequest webRequest, Exception exception) {
		LOGGER.error("[AUTENTICACAO-CONTROLLER-IMPL][Login] Erro na autenticacao do Usuario.");
		LOGGER.error(exception);

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}