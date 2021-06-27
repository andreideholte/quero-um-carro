package com.desafio.api.quc.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio.api.quc.document.Reserva;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReservaRepository extends MongoRepository<Reserva, String> {

    @Query("{domain:'?0'}")
    List<Reserva> findCustomByUsuarioIdAndPeriodo(String idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("{domain:'?0'}")
    List<Reserva> findCustomByVeiculoIdAndPeriodo(String idVeiculo, LocalDateTime dataInicio, LocalDateTime dataFim);

}