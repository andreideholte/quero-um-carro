package com.desafio.api.quc.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio.api.quc.document.Reserva;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {

    @Query("{$and :[{'usuario.id':?0}, {$or :[{dataInicio : {$gte: ?1, $lte: ?2}}, {dataFim : {$gte: ?1, $lte: ?2}} ] }] }")
    List<Reserva> findCustomByUsuarioIdAndPeriodo(String idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("{$and :[{'veiculo.id':?0}, {$or :[{dataInicio : {$gte: ?1, $lte: ?2}}, {dataFim : {$gte: ?1, $lte: ?2}} ] }] }")
    List<Reserva> findCustomByVeiculoIdAndPeriodo(String idVeiculo, LocalDateTime dataInicio, LocalDateTime dataFim);

}