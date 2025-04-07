package com.travel_website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_website.Entity.Tour;

public interface TourDAO extends JpaRepository<Tour,String>{

}
