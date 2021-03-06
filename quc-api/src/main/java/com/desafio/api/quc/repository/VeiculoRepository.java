package com.desafio.api.quc.repository;

import java.util.Optional;

import com.desafio.api.quc.document.Veiculo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends MongoRepository<Veiculo, String> {

    public Optional<Veiculo> findById(String id);
}