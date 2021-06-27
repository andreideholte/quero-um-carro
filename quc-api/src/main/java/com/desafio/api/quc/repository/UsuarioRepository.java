package com.desafio.api.quc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desafio.api.quc.document.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    public Usuario findByEmail(String email);

}