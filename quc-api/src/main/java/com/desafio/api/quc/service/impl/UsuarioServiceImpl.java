package com.desafio.api.quc.service.impl;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.desafio.api.quc.service.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UsuarioRepository usuarioRepository;

    /** 
     * Persiste um novo usuario na base de dados.
     * 
     * @param usuario a ser persistido na base de dados.
     * @return Usuario persistido com todas as informacoes, incluindo o identificador.
     */
    @Override
    public Usuario criar(Usuario usuario) {
        LOGGER.info("[USUARIO-SERVICE-IMPL][Criar] Iniciando Processo de criar usuario. Usuario [{}]", usuario);

        usuario = usuarioRepository.save(usuario);

        LOGGER.info("[USUARIO-SERVICE-IMPL][Criar] Finalizado Processo de criar usuario. Usuario Criado [{}]", usuario);
        
        return usuario;
    }
    
    /** 
     * Faz a consulta de um usuario atraves de um email.
     * 
     * @param email do usuario a ser consultado na base de dados.
     * @return Usuario encontrado na consulta que corresponde ao email informado.
     */
    @Override
    public Usuario buscarPorEmail(String email) {
        LOGGER.info("[USUARIO-SERVICE-IMPL][BuscarPorEmail] Iniciando Processo de buscar usuario por email. Email [{}]", email);

        Usuario usuario = usuarioRepository.findByEmail(email);

        LOGGER.info("[USUARIO-SERVICE-IMPL][BuscarPorEmail] Finalizado Processo de buscar usuario por email. Usuario [{}]", usuario);

        return usuario;
    }
    
    /** 
     * Atualiza os metadados de um usuario.
     * 
     * @param usuario contendo novas informacoes a serem persistidas.
     * @return Usuario atualizado com as novas informacoes.
     */
    @Override
    public Usuario atualizar(Usuario usuario) {
        LOGGER.info("[USUARIO-SERVICE-IMPL][Atualizar] Iniciando Processo de atualizar usuario. Usuario [{}]", usuario);

        usuario = usuarioRepository.save(usuario);

        LOGGER.info("[USUARIO-SERVICE-IMPL][Atualizar] Finalizado Processo de atualizar usuario. Usuario Atualizado [{}]", usuario);

        return usuario;
    }
    
    /** 
     * Deleta um usuario da base de dados.
     * 
     * @param usuario a ser deletado contendo ao menos o identificador.
     */
    @Override
    public void deletar(Usuario usuario) {
        LOGGER.info("[USUARIO-SERVICE-IMPL][Deletar] Iniciando Processo de deletar usuario. Usuario [{}]", usuario);

        usuarioRepository.delete(usuario);

        LOGGER.info("[USUARIO-SERVICE-IMPL][Deletar] Finalizado Processo de deletar usuario.");
    }

}