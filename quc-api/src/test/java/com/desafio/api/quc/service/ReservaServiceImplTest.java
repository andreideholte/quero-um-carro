package com.desafio.api.quc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservaRepository;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.desafio.api.quc.repository.VeiculoRepository;
import com.desafio.api.quc.util.Util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReservaServiceImplTest {

    @Autowired
	private ReservaService reservaService;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private VeiculoRepository veiculoRepository;
    
    @Test
    void reservaContemDatasInvalidas() throws BusinessException {
        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(Util.getReservasMock().get(0)));
        assertEquals("Periodo de reserva invalido, verifique se a data de inicio e inferior ou igual a data fim.", exception.getMessage());
    }

    @Test
    void reservaContemPeriodoInvalido() throws BusinessException {
        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(Util.getReservasMock().get(1)));
        assertEquals("Periodo de reserva invalido, o periodo maximo de reserva de um veiculo deve ser de 30 dias corridos.", exception.getMessage());
    }

    @Test
    void reservaParaUsuarioInvalido() {
        when(reservaRepository.findCustomByUsuarioIdAndPeriodo(
            Mockito.any(String.class), 
            Mockito.any(LocalDateTime.class), 
            Mockito.any(LocalDateTime.class))).thenReturn(Util.getReservasMock());

        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(Util.getReservasMock().get(3)));
        assertEquals("Este usuario ja possui uma reserva de algum veiculo no periodo informado.", exception.getMessage());
    }

    @Test
    void reservaParaVeiculoInvalido() {
        when(reservaRepository.findCustomByVeiculoIdAndPeriodo(
            Mockito.any(String.class), 
            Mockito.any(LocalDateTime.class), 
            Mockito.any(LocalDateTime.class))).thenReturn(Util.getReservasMock());

        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(Util.getReservasMock().get(3)));
        assertEquals("O veiculo escolhido ja esta reservado para um usuario no periodo informado.", exception.getMessage());
    }

    @Test
    public void reservaValida() throws BusinessException {
        Reserva novaReserva = Util.getReservasMock().get(2);
        novaReserva.setId("8s9dasdans8dna8sdn");

        when(reservaRepository.save(Mockito.any(Reserva.class)))
                .thenReturn(novaReserva);
                
        Reserva reserva = reservaService.criar(Util.getReservasMock().get(2));
        assertNotNull(reserva.getId());
    }
}
