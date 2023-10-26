package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private Long loyaltyPoints;

}
