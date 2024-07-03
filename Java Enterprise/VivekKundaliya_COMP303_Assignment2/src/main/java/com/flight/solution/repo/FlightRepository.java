package com.flight.solution.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.solution.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
}
