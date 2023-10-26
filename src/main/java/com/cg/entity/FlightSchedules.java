package com.cg.entity;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class FlightSchedules {
	
	@Id
	@NotNull
	private Long scheduleId;
	
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
