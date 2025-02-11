package com.desafio.api.quc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.desafio.api.quc.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testCriar() {
        when(usuarioRepository.save(Mockito.any(Usuario.class)))
                .thenReturn(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));

        Usuario usuarioCriado = usuarioService.criar(new Usuario(null, "andrei@teste.com", "Andrei Nascimento"));
        assertEquals("3sksda1==d8asdads8hd", usuarioCriado.getId());
    }

    @Test
    void testBuscarPorEmail() {
        when(usuarioRepository.findByEmail(Mockito.any(String.class)))
                .thenReturn(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));

        Usuario usuarioCriado = usuarioService.buscarPorEmail("andrei@teste.com");
        assertEquals("3sksda1==d8asdads8hd", usuarioCriado.getId());
    }

    @Test
    void testAtualizar() {
        when(usuarioRepository.save(Mockito.any(Usuario.class)))
                .thenReturn(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));

        Usuario usuarioAtualizado = usuarioService
                .atualizar(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));
        assertEquals("Andrei Nascimento", usuarioAtualizado.getNome());
    }
}
