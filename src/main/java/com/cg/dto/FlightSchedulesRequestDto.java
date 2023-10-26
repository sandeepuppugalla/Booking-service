package com.cg.dto;

import java.sql.Date;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FlightSchedulesRequestDto {
	
	@NotNull
	private String flightName;

	@NotNull
	private String fromCity;

	@NotNull
	private String toCity;

	@NotNull
	private Date departureDate;

	@NotNull
	private LocalTime departureTime;

	@NotNull
	private Long flightFare;

	@NotNull
	private Long totalNumberOfSeats;

	@NotNull
	private Long seatsAvailble;

}
