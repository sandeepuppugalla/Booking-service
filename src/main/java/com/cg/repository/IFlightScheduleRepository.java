package com.cg.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.FlightSchedules;

@Repository
public interface IFlightScheduleRepository extends JpaRepository<FlightSchedules, Long> {
	
	@Query(value = "Select * from public.flight_schedules where from_city=:fromCity and to_city=:toCity and departure_date=:departureDate",nativeQuery = true)
    List<FlightSchedules> findFlights(@Param("fromCity")String from, @Param("toCity") String to, @Param("departureDate") Date dateOfDeparture);

}
