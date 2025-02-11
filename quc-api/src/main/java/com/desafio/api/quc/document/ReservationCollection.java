package com.desafio.api.quc.document;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ReservationCollection implements Serializable {

    private List<Reservation> reservations;

}
