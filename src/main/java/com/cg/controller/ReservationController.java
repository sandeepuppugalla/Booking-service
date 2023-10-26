package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ReservationRequestDto;
import com.cg.dto.ReservationResponseDto;
import com.cg.entity.Reservation;
import com.cg.serviceimpl.ReservationServiceImpl;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {

	@Autowired
	private ReservationServiceImpl service;

	@GetMapping("/getAllReservations")
	public List<Reservation> getReservations() {
		return service.getReservations();
	}

	@GetMapping("/getReservationById/{reservationId}")
	public ReservationResponseDto getReservationById(@PathVariable Long reservationId) {
		return service.getReservationById(reservationId);

	}

	@PostMapping("/createReservation")
	public ReservationResponseDto createReservation(@RequestBody ReservationRequestDto request) {
		return service.createReservation(request);
	}

}
