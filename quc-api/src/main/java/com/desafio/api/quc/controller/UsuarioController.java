package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UsuarioController {

    /**
     * 
     * @param usuario
     * @return
     */
    ResponseEntity<Usuario> criar(@RequestBody final Usuario usuario);

    /**
     * 
     * @param email
     * @return
     */
    ResponseEntity<Usuario> buscarPorEmail(@RequestParam("email") final String email);

    ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario);

}