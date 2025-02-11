package com.desafio.api.quc.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.document.Veiculo;

public class Util {

    public static List<Reservation> getReservationsMock() {
        return Arrays.asList(
                new Reservation(null,
                        getRandomVeiculo(),
                        getRandomUsuario(),
                        LocalDateTime.now().plusWeeks(2L),
                        LocalDateTime.now()),
                new Reservation(null,
                        getRandomVeiculo(),
                        getRandomUsuario(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(5L)),
                new Reservation(null,
                        getRandomVeiculo(),
                        getRandomUsuario(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(2L)),
                new Reservation(null,
                        getRandomVeiculo(true),
                        getRandomUsuario(true),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusWeeks(2L)));
    }

    protected static Usuario getRandomUsuario() {
        return getRandomUsuario(false);
    }

    protected static Usuario getRandomUsuario(boolean useId) {
        return new Usuario((useId) ? "90askksd887a8s7d8agsg8a87" : null, "teste01@teste.com", "Teste 01");
    }

    protected static Veiculo getRandomVeiculo() {
        return getRandomVeiculo(false);
    }

    protected static Veiculo getRandomVeiculo(boolean useId) {
        return new Veiculo((useId) ? "lls89zzz09a09sdjs911" : null, "Wolksvagem", "Gol", "1999", "vermelho", "180393",
                getImagemVeiculo());
    }

    protected static String getImagemVeiculo() {
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSA7b4DOEzEsu0-ZO0fURRI9hYor5ECHqDzlHgiatolHQgeiAfgDmT4qVnor23hMvT_ZZU&usqp=CAU";
    }

}
