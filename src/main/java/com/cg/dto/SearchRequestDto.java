package com.cg.dto;



import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchRequestDto {
		
	@NotNull
	private String journeyDate;
	
	@NotNull
	private String fromCity;
	
	@NotNull
	private String toCity;
}
