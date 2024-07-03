package com.flight.solution.Controller;
import com.flight.solution.entity.Flight;
import com.flight.solution.repo.FlightRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.flight.solution.entity.Booking;
import com.flight.solution.entity.Passenger;
import com.flight.solution.repo.BookingRepository;


@Controller // Make sure this annotation is present
public class FlightController {
	@Autowired
    private FlightRepository flightRepository;
	
	@Autowired
    private BookingRepository bookingRepository;
	
	@GetMapping("/book-flight")
	public String showFlights(Model model, HttpSession session) {
	    if (session.getAttribute("loggedInUser") == null) {
	        return "redirect:/login"; // Redirect to login if no user is logged in
	    }
	    List<Flight> flights = flightRepository.findAll();
	    model.addAttribute("flights", flights);
	    return "book-flight";
	}
	
	@PostMapping("/book-flight")
	public String bookFlight(@RequestParam Long flightId, @RequestParam int adults, @RequestParam int children, HttpSession session, Model model) {
	    Flight flight = flightRepository.findById(flightId).orElse(null);
	    Passenger passenger = (Passenger) session.getAttribute("loggedInUser");

	    if (flight != null && passenger != null) {
	        Booking booking = new Booking();
	        booking.setFlight(flight);
	        booking.setPassenger(passenger);
	        booking.setNumberOfAdults(adults);
	        booking.setNumberOfChildren(children);
	        booking.setAmountPaid(flight.getPrice() * (adults + children * 0.5));  // Assuming children tickets are half price
	        bookingRepository.save(booking);

	        return "redirect:/confirm-booking?bookingId=" + booking.getId();
	    } else {
	        model.addAttribute("error", "There was a problem with your booking.");
	        return "book-flight";
	    }
	}
	
	@GetMapping("/confirm-booking")
	public String confirmBooking(@RequestParam Long bookingId, Model model) {
	    Booking booking = bookingRepository.findById(bookingId).orElse(null);
	    if (booking != null) {
	        model.addAttribute("booking", booking);
	        return "confirm-booking";
	    } else {
	        model.addAttribute("error", "Booking not found.");
	        return "error"; // Create an error page or redirect to a different page
	    }
	}
	
	@GetMapping("/make-payment")
	public String makePayment(@RequestParam Long bookingId, Model model) {
	    Booking booking = bookingRepository.findById(bookingId).orElse(null);
	    if (booking == null) {
	        return "redirect:/login"; // Redirect to an error page if booking not found
	    }
	    model.addAttribute("booking", booking);
	    return "payment-page"; // Your payment page view
	}
	
	@PostMapping("/process-payment")
	public String processPayment(@RequestParam Long bookingId, @RequestParam String cardNumber, @RequestParam String expiryDate, @RequestParam String cvv, Model model) {
	    // Here you would integrate with a payment gateway or service
	    // Assume payment is successful for now
	    model.addAttribute("message", "Payment successful and your booking is confirmed.");
	    return "success-page"; // Redirect to a success page
	}






}
