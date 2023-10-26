package com.cg.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PassengerRequestDto {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String phoneNo;
	
}
