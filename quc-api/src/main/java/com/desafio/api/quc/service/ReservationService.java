package com.desafio.api.quc.service;

import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.exception.BusinessException;

public interface ReservationService {

    Reservation create(final Reservation reservation) throws BusinessException;

}
