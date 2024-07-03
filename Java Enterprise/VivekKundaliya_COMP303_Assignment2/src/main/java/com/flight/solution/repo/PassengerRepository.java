package com.flight.solution.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.solution.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByEmail(String email);
}