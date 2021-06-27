package com.desafio.api.quc.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.document.Veiculo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ReservaServiceImplTest {

    @MockBean
	private ReservaService reservaService;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    void reservaContemDatasInvalidas() {

    }

    @Test
    void reservaContemPeriodoInvalido() {

    }

    @Test
    void reservaParaUsuarioInvalido() {

    }

    @Test
    void reservaParaVeiculoInvalido() {

    }

    @Test
    public void setsIdOnSave() {
        // Reserva reservaMarco = reservaRepository.save(new Person("Diego", "Samuel", "Asa Sul"));
        // assertThat(diego.getIdPerson()).isNotNull();
    }

    private List<Reserva> getReservasMock() {
        return Arrays.asList(
                new Reserva("isa0d9sdas77da", 
                        this.getRandomVeiculo(), 
                        this.getRandomUsuario(), 
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(2L)),
                new Reserva("0d0382fff32sdas77da", 
                        this.getRandomVeiculo(), 
                        this.getRandomUsuario(), 
                        LocalDateTime.now().plusWeeks(3L),
                        LocalDateTime.now().plusWeeks(5L)));
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
