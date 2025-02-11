package com.desafio.api.quc.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.desafio.api.quc.controller.ReservationController;
import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.service.ReservationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Reservation")
@CrossOrigin(origins = "http://localhost:23000")
@RestController
public class ReservationControllerImpl implements ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationControllerImpl.class);

    @Autowired
    private ReservationService reservationService;

    @Override
    @ApiOperation(value = "Create a vehicle reservation for a user")
    @PostMapping(value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) throws BusinessException {
        logger.info("[create] Starting the process of creating a reservation. Reservation [{}]", reservation);

        Reservation reservationCreated = reservationService.create(reservation);

        logger.info("[create] Process completed of creating a reservation. Reservation created [{}]",
                reservationCreated);

        return new ResponseEntity<>(reservationCreated, HttpStatus.CREATED);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> validationViolated(WebRequest webRequest, Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> internError(WebRequest webRequest, Exception exception) {
        logger.error("[create] Error creating reservation.");
        logger.error(exception.getMessage());

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
