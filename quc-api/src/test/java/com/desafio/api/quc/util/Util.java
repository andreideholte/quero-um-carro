package com.desafio.api.quc.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.document.Veiculo;

public class Util {

    public static List<Reserva> getReservasMock() {
        return Arrays.asList(
                new Reserva(null, 
                        getRandomVeiculo(), 
                        getRandomUsuario(), 
                        LocalDateTime.now().plusWeeks(2L),
                        LocalDateTime.now()),
                new Reserva(null, 
                        getRandomVeiculo(), 
                        getRandomUsuario(), 
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(5L)),
                new Reserva(null, 
                        getRandomVeiculo(), 
                        getRandomUsuario(), 
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(2L))
            );
    }

    protected static Usuario getRandomUsuario() {
        return new Usuario(null, "teste01@teste.com", "Teste 01");
    }

    protected static Veiculo getRandomVeiculo() {
        return new Veiculo(null, "Wolksvagem", "Gol", "1999", "vermelho", "180393", getImagemVeiculo());
    }

    protected static String getImagemVeiculo() {
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSA7b4DOEzEsu0-ZO0fURRI9hYor5ECHqDzlHgiatolHQgeiAfgDmT4qVnor23hMvT_ZZU&usqp=CAU";
    }
    
}
