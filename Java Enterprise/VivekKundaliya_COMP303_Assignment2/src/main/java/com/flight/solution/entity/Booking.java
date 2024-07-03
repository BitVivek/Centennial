package com.flight.solution.entity;

import jakarta.persistence.*;
import com.flight.solution.entity.BookingStatus;




@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingNumber;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;
    
    @Enumerated(EnumType.STRING) // This will store the enum as a string in the database
    private BookingStatus status = BookingStatus.PENDING; // Default status

    private int numberOfAdults;
    private int numberOfChildren;
    private double amountPaid;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
    

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}