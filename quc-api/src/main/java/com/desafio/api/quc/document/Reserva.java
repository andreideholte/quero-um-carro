package com.desafio.api.quc.document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "reserva")
@AllArgsConstructor
public class Reserva {

    @Id
    private String id;
    private Veiculo veiculo;
    private Usuario usuario;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public boolean periodoReservaValido() {
        return dataFim.isAfter(dataInicio);
    }

    public boolean quantidadeDiasReservaValido() {
        Long qtdDias = dataInicio.until(dataFim, ChronoUnit.DAYS);
        return (qtdDias <= 30);
    }
}