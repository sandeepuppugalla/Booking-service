package com.cg.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.FlightSchedulesDto;
import com.cg.dto.PassengerDto;
import com.cg.dto.ReservationRequestDto;
import com.cg.dto.ReservationResponseDto;
import com.cg.dto.SearchRequestDto;
import com.cg.entity.FlightSchedules;
import com.cg.entity.Passenger;
import com.cg.entity.Reservation;
import com.cg.repository.IFlightScheduleRepository;
import com.cg.repository.IPassengerRepository;
import com.cg.repository.IReservationRepository;
import com.cg.service.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService {

	private static final String COMPLETED = "COMPLETED";
	private static final String FAILED = "FAILED";

//	@Autowired
//	private RestTemplate template;

	@Autowired
	private IReservationRepository reservationRepository;

	@Autowired
	private IFlightScheduleRepository scheduleRepository;

	@Autowired
	private IPassengerRepository passengerRepository;

	@Override
	public List<Reservation> getReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public ReservationResponseDto getReservationById(Long reservationId) {

		Reservation r = reservationRepository.findById(reservationId).orElseThrow(() -> new NoSuchElementException(
				"record doesnot exist for reservationId: " + reservationId.toString()));
		FlightSchedules f = r.getFlightSchedules();
		
		Passenger passenger= r.getPassenger();
		return ReservationResponseDto.builder().reservationId(reservationId).reservationStatus(r.getReservationStatus())
				.passenger(PassengerDto.builder().firstName(passenger.getFirstName()).lastName(passenger.getLastName())
						.email(passenger.getEmail()).userId(passenger.getUserId()).phoneNo(passenger.getPhoneNo())
						.build())
				.flightSchedules(FlightSchedulesDto.builder().flightName(f.getFlightName()).fromCity(f.getFromCity())
						.toCity(f.getToCity()).departureDate(f.getDepartureDate()).departureTime(f.getDepartureTime())
						.flightFare(f.getFlightFare()).build()).seatNo(r.getSeatNo())
				.build();

	}

	public ReservationResponseDto createReservation(ReservationRequestDto request) {
		List<FlightSchedules> l = scheduleRepository.findFlights(request.getFromCity(), request.getToCity(),
				request.getDepartureDate());

		FlightSchedules f = l.stream().filter(k -> k.getFlightName().equalsIgnoreCase(request.getFlightName()))
				.collect(Collectors.toList()).get(0);
		String paymentStatus = getPaymentstatus();
		Passenger passenger = passengerRepository.findById(request.getUserId()).orElseThrow(
				() -> new NoSuchElementException("record doesnot exist for userId: " + request.getUserId().toString()));
		Reservation r = Reservation.builder().flightSchedules(f).passenger(passenger).paymentStatus(paymentStatus)
				.build();
		if (COMPLETED.equalsIgnoreCase(paymentStatus)) {
			r.setReservationStatus(COMPLETED);
		} else {
			r.setReservationStatus(FAILED);
		}

		if (r.getReservationStatus().equalsIgnoreCase(COMPLETED)) {
			Long availableSeats = f.getSeatsAvailble();
			f.setSeatsAvailble(availableSeats - 1);
			r.setSeatNo(f.getTotalNumberOfSeats() - availableSeats);
		}
		r.setFlightSchedules(f);
		Reservation res = reservationRepository.save(r);

		return ReservationResponseDto.builder().reservationId(res.getReservationId())
				.reservationStatus(res.getReservationStatus())
				.passenger(PassengerDto.builder().firstName(passenger.getFirstName()).lastName(passenger.getLastName())
						.email(passenger.getEmail()).userId(passenger.getUserId()).phoneNo(passenger.getPhoneNo())
						.build())
				.paymentStatus(res.getPaymentStatus())
				.flightSchedules(FlightSchedulesDto.builder().flightName(f.getFlightName()).fromCity(f.getFromCity())
						.toCity(f.getToCity()).departureDate(f.getDepartureDate()).departureTime(f.getDepartureTime())
						.flightFare(f.getFlightFare()).build())
				.build();
	}

	public SearchRequestDto createRequest(ReservationRequestDto request) {
		return SearchRequestDto.builder().fromCity(request.getFromCity()).toCity(request.getToCity())
				.journeyDate(request.getDepartureDate().toString()).build();
	}

	public String getPaymentstatus() {
		return COMPLETED;
	}
}
