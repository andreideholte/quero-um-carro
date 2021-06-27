package com.desafio.api.quc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservaRepository;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.desafio.api.quc.repository.VeiculoRepository;
import com.desafio.api.quc.service.impl.ReservaServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReservaServiceImplTest {

    @TestConfiguration
    static class ReservaServiceTestConfiguration {
        @Bean
        public ReservaService reservaService() {
            return new ReservaServiceImpl();
        }
    }

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
        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(this.getReservasMock().get(0)));
        assertEquals("Periodo de reserva invalido, verifique se a data de inicio e inferior ou igual a data fim.", exception.getMessage());
    }

    @Test
    void reservaContemPeriodoInvalido() throws BusinessException {
        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(this.getReservasMock().get(1)));
        assertEquals("Periodo de reserva invalido, o periodo maximo de reserva de um veiculo deve ser de 30 dias corridos.", exception.getMessage());
    }

    @Test
    void reservaParaUsuarioInvalido() {
        when(reservaRepository.findCustomByUsuarioIdAndPeriodo(
            Mockito.any(String.class), 
            Mockito.any(LocalDateTime.class), 
            Mockito.any(LocalDateTime.class))).thenReturn(this.getReservasMock());

        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(this.getReservasMock().get(2)));
        assertEquals("Este usuario ja possui uma reserva de algum veiculo no periodo informado.", exception.getMessage());
    }

    @Test
    void reservaParaVeiculoInvalido() {
        when(reservaRepository.findCustomByVeiculoIdAndPeriodo(
            Mockito.any(String.class), 
            Mockito.any(LocalDateTime.class), 
            Mockito.any(LocalDateTime.class))).thenReturn(this.getReservasMock());

        Throwable exception = assertThrows(BusinessException.class, () -> reservaService.criar(this.getReservasMock().get(2)));
        assertEquals("O veiculo escolhido ja esta reservado para um usuario no periodo informado.", exception.getMessage());
    }

    @Test
    public void reservaValida() throws BusinessException {
        Reserva novaReserva = this.getReservasMock().get(2);
        novaReserva.setId("8s9dasdans8dna8sdn");

        when(reservaRepository.save(Mockito.any(Reserva.class)))
                .thenReturn(novaReserva);
                
        Reserva reserva = reservaService.criar(this.getReservasMock().get(2));
        assertNotNull(reserva.getId());
    }

    private List<Reserva> getReservasMock() {
        return Arrays.asList(
                new Reserva(null, 
                        this.getRandomVeiculo(), 
                        this.getRandomUsuario(), 
                        LocalDateTime.now().plusWeeks(2L),
                        LocalDateTime.now()),
                new Reserva(null, 
                        this.getRandomVeiculo(), 
                        this.getRandomUsuario(), 
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(5L)),
                new Reserva(null, 
                        this.getRandomVeiculo(), 
                        this.getRandomUsuario(), 
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(2L))
            );
    }

    private Usuario getRandomUsuario() {
        return new Usuario("8181chh19d8do12id", "teste01@teste.com", "Teste 01");
    }

    private Veiculo getRandomVeiculo() {
        return new Veiculo("aosijdas8dj9883893jd1", "Wolksvagem", "Gol", "1999", "vermelho", "180393", this.getImagemVeiculo());
    }

    private String getImagemVeiculo() {
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSA7b4DOEzEsu0-ZO0fURRI9hYor5ECHqDzlHgiatolHQgeiAfgDmT4qVnor23hMvT_ZZU&usqp=CAU";
    }
}
