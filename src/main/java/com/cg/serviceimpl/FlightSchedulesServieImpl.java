package com.cg.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ReponseModel;
import com.cg.dto.SearchRequestDto;
import com.cg.dto.SearchResponseDto;
import com.cg.entity.FlightSchedules;
import com.cg.exceptions.InvalidFormatException;
import com.cg.repository.IFlightScheduleRepository;
import com.cg.service.IFlightSchedulesService;

@Service
public class FlightSchedulesServieImpl implements IFlightSchedulesService {

	@Autowired
	private IFlightScheduleRepository scheduleRepository;

	@Override
	public List<SearchResponseDto> getScheduledFlights(SearchRequestDto request) throws ParseException {
		//validates journeyDate
		Date journeyDate=validateDate(request.getJourneyDate());
		
		// validates fromCity and ToCity
		validateCity(request.getFromCity());
		validateCity(request.getToCity());
		

		List<FlightSchedules> list = scheduleRepository.findFlights(request.getFromCity(), request.getToCity(),
				journeyDate);
		List<SearchResponseDto> res = new ArrayList<>();

		for (FlightSchedules l : list) {
			if (l.getSeatsAvailble() <= l.getTotalNumberOfSeats()) {
				res.add(SearchResponseDto.builder().flightName(l.getFlightName()).fromCity(l.getFromCity())
						.toCity(l.getToCity()).departureDate(l.getDepartureDate()).departureTime(l.getDepartureTime())
						.flightFare(l.getFlightFare()).seatsAvailble(l.getSeatsAvailble()).build());
			}
		}
		return res;

	}

	@Override
	public ReponseModel getScheduledFlightsV1(SearchRequestDto request) throws ParseException {

		Date journeyDate=validateDate(request.getJourneyDate());

		return ReponseModel.builder().flightSchedules(scheduleRepository.findFlights(request.getFromCity(), request.getToCity(),
				journeyDate)).build();

	}
	
	private Date validateDate(String dateStr) throws ParseException {
		Date date1;
        try {
        	date1=new SimpleDateFormat("yyyy-mm-dd").parse(dateStr); 
        } catch (ParseException e) {
            throw new InvalidFormatException("invalid Date format for JourneyDate");
        }
        return date1;
	}
	
	private void validateCity(String city) {
		
		if(!city.matches("^[A-Za-z]*$")) {
			throw new InvalidFormatException("Input should contain only alphabets "+ city);
		}
	}

}
