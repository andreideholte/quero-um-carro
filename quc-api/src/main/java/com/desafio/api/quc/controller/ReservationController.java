package com.desafio.api.quc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.exception.BusinessException;

public interface ReservationController {

    /**
     * Creates a new reservation.
     *
     * @param reservation the reservation to be created
     * @return the response containing the created reservation
     * @throws BusinessException if a business error occurs during the reservation
     *                           creation
     */
    ResponseEntity<Reservation> create(@RequestBody final Reservation reservation) throws BusinessException;

}