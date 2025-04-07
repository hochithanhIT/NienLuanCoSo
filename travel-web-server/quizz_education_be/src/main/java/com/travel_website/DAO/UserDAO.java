package com.travel_website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel_website.Entity.User;

public interface UserDAO extends JpaRepository<User, String> {
    
}
