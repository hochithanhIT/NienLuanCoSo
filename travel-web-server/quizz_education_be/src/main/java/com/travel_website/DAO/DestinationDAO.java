package com.travel_website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_website.Entity.Destination;

public interface DestinationDAO extends JpaRepository<Destination, Double> {

}
