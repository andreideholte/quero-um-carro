package com.desafio.api.quc.document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "reserva")
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
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