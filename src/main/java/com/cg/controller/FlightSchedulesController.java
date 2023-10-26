package com.cg.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ReponseModel;
import com.cg.dto.SearchRequestDto;
import com.cg.dto.SearchResponseDto;
import com.cg.serviceimpl.FlightSchedulesServieImpl;

@RestController
@RequestMapping(path = "/FlightSchedules", produces = "application/json")
public class FlightSchedulesController {

	@Autowired
	private FlightSchedulesServieImpl service;

	@GetMapping("/getScheduledFlights")
	public List<SearchResponseDto> getScheduledFlights(@RequestParam String fromCity, @RequestParam String toCity,
			@RequestParam String journeyDate) throws ParseException {
		
		SearchRequestDto request = SearchRequestDto.builder().fromCity(fromCity).toCity(toCity).journeyDate(journeyDate)
				.build();
		return service.getScheduledFlights(request);
	}

	@GetMapping("/v1/getScheduledFlights")
	public ReponseModel getScheduledFlightsV1(@RequestParam String fromCity, @RequestParam String toCity,
			@RequestParam String journeyDate) throws ParseException {
		SearchRequestDto request = SearchRequestDto.builder().fromCity(fromCity).toCity(toCity).journeyDate(journeyDate)
				.build();

		return service.getScheduledFlightsV1(request);
	}

}
