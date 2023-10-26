//package com.cg.resttemplate;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestTemplate;
//
//import com.cg.dto.SearchRequestDto;
//import com.cg.entity.FlightSchedules;
//
//public class FlightSchedulesRestTemplate {
//	
//	@Autowired
//	RestTemplate template;
//	public List<FlightSchedules> getFlightSchedules(SearchRequestDto request) {
////		RestTemplate template=new RestTemplate();
//		return template.postForObject("http://localhost:8007/Search/v1/getScheduledFlights",request ,List.class);
//
//	}
//
//}
