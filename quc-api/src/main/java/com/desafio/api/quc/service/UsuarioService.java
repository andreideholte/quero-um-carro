package com.desafio.api.quc.service;

import com.desafio.api.quc.document.Usuario;

public interface UsuarioService {

    Usuario criar(final Usuario usuario);
    Usuario buscarPorEmail(final String email);
    Usuario atualizar(Usuario usuario);
    void deletar(final Usuario usuario);

}