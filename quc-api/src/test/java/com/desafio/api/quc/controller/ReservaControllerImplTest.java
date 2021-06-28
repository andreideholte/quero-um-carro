package com.desafio.api.quc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservaRepository;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.desafio.api.quc.repository.VeiculoRepository;
import com.desafio.api.quc.util.Util;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ReservaControllerImplTest {

    @MockBean
    private ReservaRepository reservaRepository;
    
    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    
    @Test
    void reservaValidaResponse201() throws Exception {
        Reserva newReserva = Util.getReservasMock().get(2);

        when(reservaRepository.save(Mockito.any(Reserva.class)))
            .thenReturn(newReserva);

        String reservaJson = mapper.writeValueAsString(newReserva);
        mockMvc.perform(MockMvcRequestBuilders.post("/reserva").contentType(MediaType.APPLICATION_JSON).content(reservaJson))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void reservaValidaResponse400() throws Exception {
        Reserva newReserva = Util.getReservasMock().get(1);
        
        when(reservaRepository.save(Mockito.any(Reserva.class)))
            .thenReturn(newReserva);

        String reservaJson = mapper.writeValueAsString(newReserva);
        mockMvc.perform(MockMvcRequestBuilders.post("/reserva").contentType(MediaType.APPLICATION_JSON).content(reservaJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
            .andExpect(result -> assertEquals("Periodo de reserva invalido, o periodo maximo de reserva de um veiculo deve ser de 30 dias corridos.", 
                                            result.getResolvedException().getMessage()));
    }
}
