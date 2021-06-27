package com.desafio.api.quc.service;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.exception.BusinessException;

public interface ReservaService {

    Reserva criar(final Reserva reserva) throws BusinessException;
    
}
