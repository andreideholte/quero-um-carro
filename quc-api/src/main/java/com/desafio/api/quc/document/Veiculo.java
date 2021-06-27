package com.desafio.api.quc.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "veiculo")
@AllArgsConstructor
public class Veiculo {

    @Id
    private String id;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private String quilometragem;
    private String imagem;

}