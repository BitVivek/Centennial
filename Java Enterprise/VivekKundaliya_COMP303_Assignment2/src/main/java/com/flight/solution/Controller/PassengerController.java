package com.flight.solution.Controller;


import com.flight.solution.entity.Passenger;
import com.flight.solution.repo.*;
import com.flight.solution.entity.Booking;
import com.flight.solution.entity.BookingStatus;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;
    
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "register"; // view for registration
    }

    @PostMapping("/register")
    public String registerPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
        return "redirect:/login"; // redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // view for login
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Passenger passenger = passengerRepository.findByEmail(email);
        if (passenger != null && passenger.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", passenger);
            return "redirect:/book-flight"; // redirect to the booking page if credentials are correct
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // return to login page if credentials are incorrect
        }
    }
    
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        Passenger passenger = (Passenger) session.getAttribute("loggedInUser");
        if (passenger == null) {
            return "redirect:/login"; // Redirect to login if no user is logged in
        }
        List<Booking> bookings = bookingRepository.findByPassengerId(passenger.getId());
        model.addAttribute("passenger", passenger);
        model.addAttribute("bookings", bookings);
        return "profile"; // profile.html
    }
    
    @PostMapping("/update-profile")
    public String updateProfile(Passenger passenger, HttpSession session) {
        Passenger existingPassenger = (Passenger) session.getAttribute("loggedInUser");
        if (existingPassenger == null) {
            return "redirect:/login";
        }
        existingPassenger.setFirstName(passenger.getFirstName());
        existingPassenger.setEmail(passenger.getEmail());
        existingPassenger.setPassword(passenger.getPassword()); // Consider using a password encoder here
        passengerRepository.save(existingPassenger);
        return "redirect:/profile";
    }
    
    @GetMapping("/cancel-booking")
    public String cancelBooking(@RequestParam Long bookingId, RedirectAttributes redirectAttributes) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
            redirectAttributes.addFlashAttribute("message", "Booking cancelled successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Booking not found.");
        }

        return "redirect:/profile"; // Assuming "/" is your home page
    }
    
    


}