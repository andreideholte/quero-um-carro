package com.desafio.api.quc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;

import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AutenticacaoControllerImplTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    
    @Test
    void loginUsuarioValido() throws Exception {
        when(usuarioRepository.findByEmail(Mockito.anyString()))
                .thenReturn(new Usuario("ss9a9sda80vvdkksdva8", "andrei@teste.com", "Andrei Nascimento"));

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/login").param("email", "andrei@teste.com").param("nome", "Andrei Nascimento").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(result -> assertEquals("ss9a9sda80vvdkksdva8", this.getObject(result).getId()));
    }

    @Test
    void loginUsuarioEmailNovo() throws Exception {
        when(usuarioRepository.findByEmail(Mockito.anyString()))
            .thenReturn(null);

        when(usuarioRepository.save(Mockito.any(Usuario.class)))
            .thenReturn(new Usuario("ss9a9sda80vvdkksdva8", "novoEmail@teste.com", "Andrei Nascimento"));

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/login").param("email", "novoEmail@teste.com").param("nome", "Andrei Nascimento").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(result -> assertEquals("ss9a9sda80vvdkksdva8", this.getObject(result).getId()));
    }

    @Test
    void loginUsuarioNomeDiferente() throws Exception {
        when(usuarioRepository.findByEmail(Mockito.anyString()))
            .thenReturn(new Usuario("ss9a9sda80vvdkksdva8", "andrei@teste.com", "Antigo Nome"));

        when(usuarioRepository.save(Mockito.any(Usuario.class)))
            .thenReturn(new Usuario("ss9a9sda80vvdkksdva8", "andrei@teste.com", "Novo Nome"));

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/login").param("email", "andrei@teste.com").param("nome", "Novo Nome").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(result -> assertEquals("ss9a9sda80vvdkksdva8", this.getObject(result).getId()));
    }

    private Usuario getObject(MvcResult result) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
        return mapper.readValue(result.getResponse().getContentAsString(), Usuario.class);
    }
}
