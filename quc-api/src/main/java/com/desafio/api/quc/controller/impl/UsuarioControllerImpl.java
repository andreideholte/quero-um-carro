package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.UsuarioController;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.service.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Usuário")
@RestController
public class UsuarioControllerImpl implements UsuarioController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
	private UsuarioService usuarioService;

	@Override
	@ApiOperation(value = "Criar um usuario")
	@PostMapping(value= "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        LOGGER.info("[USUARIO-CONTROLLER-IMPL][Criar] Iniciando Processo de criar usuario. Usuario [{}]", usuario);

        Usuario usuarioCriado = usuarioService.criar(usuario);

        LOGGER.info("[USUARIO-CONTROLLER-IMPL][Criar] Finalizado Processo de criar usuario. Usuario Criado [{}]", usuarioCriado);

        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
	}

	@Override
	@ApiOperation(value = "Buscar um usuario pelo email - Param obrigatorio")
	@GetMapping(value= "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscarPorEmail(@RequestParam("email") String email) {
        LOGGER.info("[USUARIO-CONTROLLER-IMPL][BuscarPorEmail] Iniciando Processo de buscar usuario por email. Email [{}]", email);

        Usuario usuario = usuarioService.buscarPorEmail(email);

        LOGGER.info("[USUARIO-CONTROLLER-IMPL][BuscarPorEmail] Finalizado Processo de buscar usuario por email. Usuario Encontrado [{}]", usuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Atualizar um usuario")
	@PutMapping(value= "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
        LOGGER.info("[USUARIO-CONTROLLER-IMPL][Atualizar] Iniciando Processo de atualizar usuario. Usuario [{}]", usuario);

        Usuario usuarioAtualizado = usuarioService.atualizar(usuario);

        LOGGER.info("[USUARIO-CONTROLLER-IMPL][BuscarPorEmail] Finalizado Processo de atualizar usuario. Usuario Atualizado [{}]", usuarioAtualizado);

        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
	}	
}