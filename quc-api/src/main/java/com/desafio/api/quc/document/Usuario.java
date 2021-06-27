package com.desafio.api.quc.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "usuario")
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String nome;

}