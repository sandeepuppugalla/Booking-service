package com.cg.service;

import java.text.ParseException;
import java.util.List;

import com.cg.dto.ReponseModel;
import com.cg.dto.SearchRequestDto;
import com.cg.dto.SearchResponseDto;

public interface IFlightSchedulesService {
	
	List<SearchResponseDto> getScheduledFlights(SearchRequestDto request) throws ParseException;
	ReponseModel getScheduledFlightsV1(SearchRequestDto request) throws ParseException;
	
}
