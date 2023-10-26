package com.cg.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.PassengerDto;
import com.cg.dto.PassengerRequestDto;
import com.cg.dto.PassengerResponseDto;
import com.cg.dto.PassengerUpdateRequestDto;
import com.cg.entity.Passenger;
import com.cg.repository.IPassengerRepository;
import com.cg.service.IPassengerService;

@Service
public class PassengerServiceImpl implements IPassengerService {

	@Autowired
	private IPassengerRepository repository;

	@Override
	public Passenger savePassenger(PassengerRequestDto request) {
		Passenger passenger = Passenger.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.phoneNo(request.getPhoneNo()).email(request.getEmail()).build();

		return repository.save(passenger);

	}
	
	@Override
	public Passenger updatePassenger(PassengerUpdateRequestDto request) {
		Passenger passenger = Passenger.builder().userId(request.getUserId()).firstName(request.getFirstName()).lastName(request.getLastName())
				.phoneNo(request.getPhoneNo()).email(request.getEmail()).loyaltyPoints(request.getLoyaltyPoints()).build();

		return repository.save(passenger);

	}

	@Override
	public PassengerResponseDto getPassengerById(Long id) {

		List<PassengerDto> l = new ArrayList<>();

		Passenger p = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("record does not exist for userId: " + id.toString()));

		l.add(PassengerDto.builder().userId(p.getUserId()).firstName(p.getFirstName()).lastName(p.getLastName())
				.email(p.getEmail()).phoneNo(p.getPhoneNo()).loyaltyPoints(p.getLoyaltyPoints()).build());
		return PassengerResponseDto.builder().passengers(l).build();
	}

	@Override
	public PassengerResponseDto getAllPassengers() {
		// need to change
		return PassengerResponseDto.builder().build();
	}

}
