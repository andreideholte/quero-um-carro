package com.desafio.api.quc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservationRepository;
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
public class ReservationControllerImplTest {

        @MockBean
        private ReservationRepository reservationRepository;

        @MockBean
        private UsuarioRepository userRepository;

        @MockBean
        private VeiculoRepository vehicleRepository;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper mapper;

        @Test
        void validReservationResponse201() throws Exception {
                Reservation newReservation = Util.getReservationsMock().get(2);

                when(reservationRepository.save(Mockito.any(Reservation.class)))
                                .thenReturn(newReservation);

                String reservationJson = mapper.writeValueAsString(newReservation);
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/reservation").contentType(MediaType.APPLICATION_JSON)
                                                .content(reservationJson))
                                .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @Test
        void validReservationResponse400() throws Exception {
                Reservation newReservation = Util.getReservationsMock().get(1);

                when(reservationRepository.save(Mockito.any(Reservation.class)))
                                .thenReturn(newReservation);

                String reservationJson = mapper.writeValueAsString(newReservation);
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/reservation").contentType(MediaType.APPLICATION_JSON)
                                                .content(reservationJson))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(result -> assertTrue(
                                                result.getResolvedException() instanceof BusinessException))
                                .andExpect(result -> assertEquals(
                                                "Invalid reservation period, the maximum reservation period for a vehicle must be 30 consecutive days.",
                                                result.getResolvedException().getMessage()));
        }
}
