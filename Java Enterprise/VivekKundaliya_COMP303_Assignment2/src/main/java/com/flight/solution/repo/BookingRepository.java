package com.flight.solution.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.solution.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByPassengerId(Long id);
}