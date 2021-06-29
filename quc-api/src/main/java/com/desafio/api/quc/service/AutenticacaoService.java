package com.desafio.api.quc.service;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.exception.UsuarioDesatualizadoException;
import com.desafio.api.quc.exception.UsuarioNaoExisteException;

public interface AutenticacaoService {
    
    Usuario autenticar(final String email, final String nome) throws UsuarioNaoExisteException, UsuarioDesatualizadoException;
}
