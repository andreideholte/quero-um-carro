package com.desafio.api.quc.document;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ReservasCollection implements Serializable {
    
    private List<Reserva> reservas;

}
