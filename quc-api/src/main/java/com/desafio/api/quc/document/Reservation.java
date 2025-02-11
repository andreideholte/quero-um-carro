package com.desafio.api.quc.document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "reservation")
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private static final Integer MAX_PERIOD_IN_DAYS = 30;

    @Id
    private String id;
    private Veiculo vehicle;
    private Usuario user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Checks whether the reservation period is valid.
     * 
     * @return true if the end date is after the start date, otherwise false.
     */
    public boolean isReservationPeriodValid() {
        return endDate.isAfter(startDate);
    }

    /**
     * Checks whether the number of reservation days is valid.
     * 
     * @return true if the reservation period does not exceed the maximum allowed
     *         days, otherwise false.
     */
    public boolean isReservationDaysValid() {
        Long quantityDays = startDate.until(endDate, ChronoUnit.DAYS);
        return (quantityDays <= MAX_PERIOD_IN_DAYS);
    }
}