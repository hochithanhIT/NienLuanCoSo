package com.travel_website.RestController;

import java.util.List;
import java.util.Optional;

// import javax.persistence.Entity;

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

import com.travel_website.DAO.TourDAO;
import com.travel_website.Entity.Tour;
// import com.travel_website.Entity.Tour_type;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dulich/api/tour")
public class TourRestController {
    @Autowired
    TourDAO tourDAO;

    @GetMapping
    public ResponseEntity<List<Tour>> findAll() {
        return ResponseEntity.ok(tourDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Tour> findById(@PathVariable("id") String tour_id) {
        Optional<Tour> optional = tourDAO.findById(tour_id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @PostMapping()
    public ResponseEntity<Tour> post(@RequestBody Tour tour) {
        if (tourDAO.existsById(tour.getTour_id())) {
            return ResponseEntity.badRequest().build();
        }
        tourDAO.save(tour);
        return ResponseEntity.ok(tour);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tour> put(@PathVariable("id") String tour_id, @RequestBody Tour tour) {
        if (!tourDAO.existsById(tour_id)) {
            return ResponseEntity.notFound().build();
        }
        tourDAO.save(tour);
        return ResponseEntity.ok(tour);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String tour_id) {
        if (!tourDAO.existsById(tour_id)) {
            return ResponseEntity.notFound().build();
        }
        tourDAO.deleteById(tour_id);
        return ResponseEntity.ok().build();
    }
}
