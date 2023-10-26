package com.cg.service;

import com.cg.dto.PassengerRequestDto;
import com.cg.dto.PassengerResponseDto;
import com.cg.dto.PassengerUpdateRequestDto;
import com.cg.entity.Passenger;

public interface IPassengerService {

	Passenger savePassenger(PassengerRequestDto request);
	PassengerResponseDto getAllPassengers();
	PassengerResponseDto getPassengerById(Long id);
	Passenger updatePassenger(PassengerUpdateRequestDto request);
}
