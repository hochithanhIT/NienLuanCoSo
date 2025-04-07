package com.travel_website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_website.Entity.Booking;

public interface BookingDAO extends JpaRepository<Booking, String> {

}
