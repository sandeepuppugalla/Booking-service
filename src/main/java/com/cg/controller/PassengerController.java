package com.cg.controller;

import javax.servlet.annotation.HttpConstraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.PassengerRequestDto;
import com.cg.dto.PassengerResponseDto;
import com.cg.dto.PassengerUpdateRequestDto;
import com.cg.entity.Passenger;
import com.cg.serviceimpl.PassengerServiceImpl;

@RestController
@RequestMapping("/Passenger")
public class PassengerController {

	@Autowired
	private PassengerServiceImpl service;

	@PostMapping("/savePassenger")
	public ResponseEntity<Passenger> savePassenger(@RequestBody PassengerRequestDto request) {
		return new ResponseEntity<>(service.savePassenger(request), HttpStatus.CREATED);
	}

	@GetMapping(path="/getPassenger/{userId}"
			//,params="version"
			,produces="application/xml"
			)
	public PassengerResponseDto getPassengerById(
			//@RequestHeader(HttpHeaders.ACCEPT) String contentType,
			@PathVariable Long userId) {
		
		return service.getPassengerById(userId);
	}

	@GetMapping("/getAllPassengers")
	public ResponseEntity<PassengerResponseDto> getAllPassenger() {
		return new ResponseEntity<>(service.getAllPassengers(), HttpStatus.OK);
	}
	
	@PutMapping("/updatePassenger")
	public ResponseEntity<String> updatePassenger(@RequestBody PassengerUpdateRequestDto request) {
		service.updatePassenger(request);
		return new ResponseEntity<>("Updated", HttpStatus.CREATED);
	}

}
