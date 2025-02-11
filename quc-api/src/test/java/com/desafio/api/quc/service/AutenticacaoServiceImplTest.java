package com.desafio.api.quc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.exception.UsuarioDesatualizadoException;
import com.desafio.api.quc.exception.UsuarioNaoExisteException;
import com.desafio.api.quc.service.impl.AutenticacaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AutenticacaoServiceImplTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private AutenticacaoServiceImpl autenticacaoService;

    @Test
    void testUsuarioNaoExiste() {
        when(usuarioService.buscarPorEmail("email@teste.com")).thenReturn(null);

        assertThrows(UsuarioNaoExisteException.class, () -> {
            autenticacaoService.autenticar("email@teste.com", "Nome Teste");
        });
    }

    @Test
    void testUsuarioDesatualizado() {
        Usuario usuario = new Usuario();
        usuario.setEmail("email@teste.com");
        usuario.setNome("Nome Antigo");

        when(usuarioService.buscarPorEmail("email@teste.com")).thenReturn(usuario);

        assertThrows(UsuarioDesatualizadoException.class, () -> {
            autenticacaoService.autenticar("email@teste.com", "Nome Teste");
        });
    }

    @Test
    void testAutenticacaoBemSucedida() throws UsuarioNaoExisteException, UsuarioDesatualizadoException {
        Usuario usuario = new Usuario();
        usuario.setEmail("email@teste.com");
        usuario.setNome("Nome Teste");

        when(usuarioService.buscarPorEmail("email@teste.com")).thenReturn(usuario);

        Usuario usuarioAutenticado = autenticacaoService.autenticar("email@teste.com", "Nome Teste");

        assertEquals(usuario, usuarioAutenticado);
    }
}