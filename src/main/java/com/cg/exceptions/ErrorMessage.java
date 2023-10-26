package com.cg.exceptions;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	  private int statusCode;
	  private Date timestamp;
//	  private String message;
	  private  Map<String, String> errors ;
	  private String description;
	  
}
