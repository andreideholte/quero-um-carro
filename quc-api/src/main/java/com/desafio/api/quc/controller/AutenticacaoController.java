package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.exception.UsuarioDesatualizadoException;
import com.desafio.api.quc.exception.UsuarioNaoExisteException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface AutenticacaoController {

    /**
     * Realiza o login de um usuário.
     *
     * @param email o email do usuário
     * @param nome  o nome do usuário
     * @return a resposta contendo o usuário autenticado
     * @throws UsuarioNaoExisteException     se o usuário não existir
     * @throws UsuarioDesatualizadoException se o nome do usuário estiver
     *                                       desatualizado
     */
    public ResponseEntity<Usuario> login(@RequestParam("email") final String email,
            @RequestParam("nome") final String nome) throws UsuarioNaoExisteException, UsuarioDesatualizadoException;

}