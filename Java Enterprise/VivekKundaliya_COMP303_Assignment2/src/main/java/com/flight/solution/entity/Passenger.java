package com.flight.solution.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "passengers")
public class Passenger {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String email;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String phoneNumber;
	    private String address;
	    private String city;
	    private String postalCode;
	    private String country;

	    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
	    private List<Booking> bookings;
	    

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getPostalCode() {
	        return postalCode;
	    }

	    public void setPostalCode(String postalCode) {
	        this.postalCode = postalCode;
	    }

	    public String getCountry() {
	        return country;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }

	    public List<Booking> getBookings() {
	        return bookings;
	    }

	    public void setBookings(List<Booking> bookings) {
	        this.bookings = bookings;
	    }
}