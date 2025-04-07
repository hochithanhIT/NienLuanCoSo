package com.travel_website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_website.Entity.Admin;

public interface AdminDAO extends JpaRepository<Admin, String> {
    
}
