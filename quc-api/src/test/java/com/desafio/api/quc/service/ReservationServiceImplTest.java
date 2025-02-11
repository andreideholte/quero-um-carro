package com.desafio.api.quc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafio.api.quc.document.Reservation;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservationRepository;
import com.desafio.api.quc.repository.UsuarioRepository;
import com.desafio.api.quc.repository.VeiculoRepository;
import com.desafio.api.quc.service.impl.ReservationServiceImpl;
import com.desafio.api.quc.util.Util;

@ExtendWith(SpringExtension.class)
public class ReservationServiceImplTest {

	@InjectMocks
	private ReservationServiceImpl reservationService;

	@Mock
	private ReservationRepository reservationRepository;

	@Mock
	private UsuarioRepository userRepository;

	@Mock
	private VeiculoRepository vehicleRepßository;

	@Test
	void reservationContainsInvalidDates() throws BusinessException {
		Throwable exception = assertThrows(BusinessException.class,
				() -> reservationService.create(Util.getReservationsMock().get(0)));
		assertEquals(
				"Invalid reservation period, please check if the start date is earlier than or equal to the end date.",
				exception.getMessage());
	}

	@Test
	void reservationContainsInvalidPeriod() throws BusinessException {
		Throwable exception = assertThrows(BusinessException.class,
				() -> reservationService.create(Util.getReservationsMock().get(1)));
		assertEquals(
				"Invalid reservation period, the maximum reservation period for a vehicle must be 30 consecutive days.",
				exception.getMessage());
	}

	@Test
	void reservationForInvalidUser() {
		when(reservationRepository.findCustomByUserIdAndPeriod(
				Mockito.any(String.class),
				Mockito.any(LocalDateTime.class),
				Mockito.any(LocalDateTime.class))).thenReturn(Util.getReservationsMock());

		Throwable exception = assertThrows(BusinessException.class,
				() -> reservationService.create(Util.getReservationsMock().get(3)));
		assertEquals("This user already has a vehicle reservation for the specified period.",
				exception.getMessage());
	}

	@Test
	void reservationForInvalidVehicle() {
		when(reservationRepository.findCustomByVehicleIdAndPeriod(
				Mockito.any(String.class),
				Mockito.any(LocalDateTime.class),
				Mockito.any(LocalDateTime.class))).thenReturn(Util.getReservationsMock());

		Throwable exception = assertThrows(BusinessException.class,
				() -> reservationService.create(Util.getReservationsMock().get(3)));
		assertEquals("The selected vehicle is already reserved for a user during the specified period.",
				exception.getMessage());
	}

	@Test
	public void validReservation() throws BusinessException {
		Reservation newReservation = Util.getReservationsMock().get(2);
		newReservation.setId("8s9dasdans8dna8sdn");

		when(reservationRepository.save(Mockito.any(Reservation.class)))
				.thenReturn(newReservation);

		Reservation reservationCreated = reservationService.create(Util.getReservationsMock().get(2));
		assertNotNull(reservationCreated.getId());
	}
}
