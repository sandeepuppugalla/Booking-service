package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Passenger;

public interface IPassengerRepository extends JpaRepository<Passenger, Long> {

}
