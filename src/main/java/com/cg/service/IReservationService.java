package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.dto.ReservationResponseDto;
import com.cg.entity.Reservation;

@Service
public interface IReservationService {
	
	List<Reservation> getReservations();
	ReservationResponseDto getReservationById(Long reservationId);	

}
