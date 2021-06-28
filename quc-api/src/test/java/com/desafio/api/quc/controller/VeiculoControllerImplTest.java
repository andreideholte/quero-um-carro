package com.desafio.api.quc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Optional;

import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.document.VeiculosCollection;
import com.desafio.api.quc.repository.VeiculoRepository;
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
public class VeiculoControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    
    @MockBean
    private VeiculoRepository veiculoRepository;

    @Test
    void testBuscarPorId() throws Exception {
        when(veiculoRepository.findById(Mockito.any(String.class)))
            .thenReturn(Optional.of(new Veiculo(
                "90as9=sd299zz3dfg", 
                "Renault", 
                "Sandero", 
                "2011", 
                "Prata", 
                "322393", 
                "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg")));

        mockMvc.perform(MockMvcRequestBuilders.get("/veiculo/{id}", "90as9=sd299zz3dfg").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(result -> assertEquals("Sandero", this.getObject(result).getModelo()));
    }

    @Test
    void testCriar() throws Exception {
        when(veiculoRepository.save(Mockito.any(Veiculo.class)))
            .thenReturn(new Veiculo(
                "90as9=sd299zz3dfg", 
                "Renault", 
                "Sandero", 
                "2011", 
                "Prata", 
                "322393", 
                "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg"));

        String veiculoJson = mapper.writeValueAsString(new Veiculo(
            null, 
            "Renault", 
            "Sandero", 
            "2011", 
            "Prata", 
            "322393", 
            "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg"));

        mockMvc.perform(MockMvcRequestBuilders.post("/veiculo").contentType(MediaType.APPLICATION_JSON).content(veiculoJson))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testListarTodos() throws Exception {
        when(veiculoRepository.findAll())
            .thenReturn(Arrays.asList(new Veiculo(
                "90as9=sd299zz3dfg", 
                "Renault", 
                "Sandero", 
                "2011", 
                "Prata", 
                "322393", 
                "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg")));
                
        mockMvc.perform(MockMvcRequestBuilders.get("/veiculo").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(result -> assertEquals(1, this.getList(result).getVeiculos().size()));
    }

    private Veiculo getObject(MvcResult result) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
        return mapper.readValue(result.getResponse().getContentAsString(), Veiculo.class);
    }

    private VeiculosCollection getList(MvcResult result) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
        return mapper.readValue(result.getResponse().getContentAsString(), VeiculosCollection.class);
    }
}
