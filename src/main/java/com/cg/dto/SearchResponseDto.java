package com.cg.dto;

import java.sql.Date;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDto {

	private String flightName;
	private String fromCity;
	private String toCity;
	private Date departureDate;
	private LocalTime departureTime;
	private Long flightFare;
	private Long seatsAvailble;

}
