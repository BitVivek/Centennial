package com.flight.solution;


import com.flight.solution.entity.Flight;
import com.flight.solution.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
public class FlightDataLoader implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        if (flightRepository.count() == 0) {
            List<Flight> flights = new ArrayList<>();
            IntStream.range(1, 51).forEach(i -> {
                flights.add(createFlight(i));
            });
            flightRepository.saveAll(flights);
        }
    }

    private Flight createFlight(int index) {
        Flight flight = new Flight();
        flight.setFlightNumber("FL" + String.format("%04d", index));
        flight.setAirlineName("Airline " + index);
        flight.setDepartureTime(randomDate());
        flight.setArrivalTime(randomDate());
        flight.setPrice(100.0 + index * 5);
        flight.setAirlineLogo("logo" + index + ".png"); // Ensure these images exist in your static resources
        return flight;
    }

    private Date randomDate() {
        long minDay = LocalDate.of(2022, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return Date.from(LocalDate.ofEpochDay(randomDay).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}