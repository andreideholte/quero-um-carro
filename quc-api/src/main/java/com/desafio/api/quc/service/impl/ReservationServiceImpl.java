package com.desafio.api.quc.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservationRepository;
import com.desafio.api.quc.service.ReservationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Creates a new vehicle reservation for a user for a maximum period of thirty
     * days.
     * 
     * @param reserva the reservation to be persisted in the database.
     * @return The persisted reservation with all information, including the
     *         identifier.
     * @throws BusinessException
     */
    @Override
    public Reservation create(Reservation reservation) throws BusinessException {
        logger.debug("[create] Iniciando Processo de criar uma reserva. Reserva [{}]", reservation);

        this.validateReservation(reservation);

        reservation = reservationRepository.save(reservation);

        logger.debug("[create] Finalizado Processo de criar uma reserva. Reserva Criada [{}]", reservation);

        return reservation;
    }

    /**
     * Validates all reservation preconditions before persisting it in the database.
     * If any precondition fails, the validation will throw an exception with the
     * appropriate error message.
     * 
     * @param reservation the reservation data to be validated.
     * @throws BusinessException
     */
    private void validateReservation(Reservation reservation) throws BusinessException {
        logger.debug("[validateReservation] Starting process to validate reservation.");

        if (!reservation.isReservationPeriodValid()) {
            throw new BusinessException(
                    "Invalid reservation period, please check if the start date is earlier than or equal to the end date.");
        }

        if (!reservation.isReservationDaysValid()) {
            throw new BusinessException(
                    "Invalid reservation period, the maximum reservation period for a vehicle must be 30 consecutive days.");
        }

        if (this.hasUserReservationInPeriod(reservation.getUser().getId(), reservation.getStartDate(),
                reservation.getEndDate())) {
            throw new BusinessException("This user already has a vehicle reservation for the specified period.");
        }

        if (this.hasVehicleReservationInPeriod(reservation.getVehicle().getId(), reservation.getStartDate(),
                reservation.getEndDate())) {
            throw new BusinessException(
                    "The selected vehicle is already reserved for a user during the specified period.");
        }

        logger.debug("[validateReservation] Process completed to validate reservation.");
    }

    /**
     * Validates whether the user has only that reservation for the specified date.
     *
     * @param userId    the ID of the user making the reservation.
     * @param startDate the start date of the vehicle reservation period.
     * @param endDate   the end date of the vehicle reservation period.
     * @return {@code boolean} indicating whether the reservation period is valid
     *         for that user.
     */
    private boolean hasUserReservationInPeriod(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug(
                "[hasUserReservationInPeriod] Starting process to validate user reservations. User ID [{}], Start Date [{}], End Date [{}]",
                userId, startDate, endDate);

        List<Reservation> reservations = reservationRepository.findCustomByUserIdAndPeriod(userId, startDate, endDate);

        logger.debug(
                "[hasUserReservationInPeriod] Process completed to validate user reservations. User ID [{}], Start Date [{}], End Date [{}]",
                userId, startDate, endDate);

        return (reservations != null && !reservations.isEmpty());
    }

    /**
     * Validates whether the vehicle is already reserved for another user on the
     * specified date.
     * 
     * @param vehicleId the ID of the vehicle being checked.
     * @param startDate the start date of the vehicle's reservation period.
     * @param endDate   the end date of the vehicle's reservation period.
     * @return {@code boolean} indicating whether the vehicle is available.
     */
    private boolean hasVehicleReservationInPeriod(String vehicleId, LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug(
                "[hasVehicleReservationInPeriod] Starting process to validate vehicle reservations. Vehicle ID [{}], Start Date [{}], End Date [{}]",
                vehicleId, startDate, endDate);

        List<Reservation> reservations = reservationRepository.findCustomByVehicleIdAndPeriod(vehicleId, startDate,
                endDate);

        logger.debug(
                "[hasVehicleReservationInPeriod] Process completed to validate vehicle reservations. Vehicle ID [{}], Start Date [{}], End Date [{}]",
                vehicleId, startDate, endDate);

        return (reservations != null && !reservations.isEmpty());
    }

}