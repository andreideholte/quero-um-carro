package com.desafio.api.quc.document;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class VeiculosCollection implements Serializable {

    private List<Veiculo> veiculos;
    
}
