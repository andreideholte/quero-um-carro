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
public class UsuarioControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void testAtualizar() throws Exception {
        when(usuarioRepository.save(Mockito.any(Usuario.class)))
            .thenReturn(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));

        String usuarioJson = mapper.writeValueAsString(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));
        mockMvc.perform(MockMvcRequestBuilders.put("/usuario").contentType(MediaType.APPLICATION_JSON).content(usuarioJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(result -> assertEquals("Andrei Nascimento", this.getObject(result).getNome()));
    }

    @Test
    void testBuscarPorEmail() throws Exception {
        when(usuarioRepository.findByEmail(Mockito.any(String.class)))
            .thenReturn(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuario").param("email", "andrei@teste.com").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(result -> assertEquals("3sksda1==d8asdads8hd", this.getObject(result).getId()));
    }

    @Test
    void testCriar() throws Exception {
        when(usuarioRepository.save(Mockito.any(Usuario.class)))
            .thenReturn(new Usuario("3sksda1==d8asdads8hd", "andrei@teste.com", "Andrei Nascimento"));

        String usuarioJson = mapper.writeValueAsString(new Usuario(null, "andrei@teste.com", "Andrei Nascimento"));
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario").contentType(MediaType.APPLICATION_JSON).content(usuarioJson))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private Usuario getObject(MvcResult result) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
        return mapper.readValue(result.getResponse().getContentAsString(), Usuario.class);
    }
}
