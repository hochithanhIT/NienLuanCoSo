package com.travel_website.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel_website.DAO.BookingDAO;
// import com.travel_website.Entity.Admin;
import com.travel_website.Entity.Booking;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dulich/api/booking")
public class BookingRestController {
    @Autowired
    BookingDAO bookingDAO;

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(bookingDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") String IdBooking) {
        Optional<Booking> optional = bookingDAO.findById(IdBooking);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @PostMapping()
    public ResponseEntity<Booking> post(@RequestBody Booking booking) {
        // Sinh UUID cho booking_id
        String bookingId = UUID.randomUUID().toString();
        // Gán UUID cho thuộc tính booking_id của booking
        booking.setBooking_id(bookingId);
        if (bookingDAO.existsById(booking.getBooking_id())) {
            return ResponseEntity.badRequest().build();
        }
        bookingDAO.save(booking);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("{id}")
    public ResponseEntity<Booking> put(@PathVariable("id") String IdBooking, @RequestBody Booking booking) {
        if (!bookingDAO.existsById(IdBooking)) {
            return ResponseEntity.notFound().build();
        }
        bookingDAO.save(booking);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String IdBooking) {
        if (!bookingDAO.existsById(IdBooking)) {
            return ResponseEntity.notFound().build();
        } 
        bookingDAO.deleteById(IdBooking);
        return ResponseEntity.ok().build();
    }
}
