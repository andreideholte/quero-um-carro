package com.desafio.api.quc.service.impl;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.exception.UsuarioDesatualizadoException;
import com.desafio.api.quc.exception.UsuarioNaoExisteException;
import com.desafio.api.quc.service.AutenticacaoService;
import com.desafio.api.quc.service.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private static final Logger LOGGER = LogManager.getLogger();
    
    @Autowired
    UsuarioService usuarioService;

    /** 
     * Faz a validacao do email e nome enviado para autenticacao do usuario no sistema.
     * Esta maneira, e uma maneira simples e nao ideal para o mundo real, existem outras maneiras
     * de autenticacao que protegem de fato a api, seja por jtw ou autenticacao basica seguindo os 
     * fluxos do oauth 2.0
     * 
     * @param email que sera usado para validacao no sistema.
     * @param nome para validar se o usuario do email acima necessita de uma atualizacao.
     * @return Usuario autenticado com o email valido e nome sem alteracao na base de dados.
     * @throws UsuarioNaoExisteException
     * @throws UsuarioDesatualizadoException
     */
    @Override
    public Usuario autenticar(String email, String nome) throws UsuarioNaoExisteException, UsuarioDesatualizadoException {
        LOGGER.debug("[AUTENTICACAO-SERVICE-IMPL][Autenticar] Iniciando Processo de autenticacao do email informado. Email [{}], Nome [{}]", email, nome);

        Usuario usuarioEncontrado = usuarioService.buscarPorEmail(email);

        if (usuarioEncontrado == null) {
            throw new UsuarioNaoExisteException();
        }

        if (!usuarioEncontrado.getNome().equals(nome)) {
            throw new UsuarioDesatualizadoException();
        }

        LOGGER.debug("[AUTENTICACAO-SERVICE-IMPL][Autenticar] Finalizado Processo de autenticacao do email informado. Usuario Autenticado [{}]", usuarioEncontrado);
        
        return usuarioEncontrado;
    }
    
}
