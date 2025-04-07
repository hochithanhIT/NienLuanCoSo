package com.travel_website.RestController;

import java.util.List;
import java.util.Optional;

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

import com.travel_website.DAO.Tour_typeDAO;
import com.travel_website.Entity.Tour_type;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dulich/api/tour_type")
public class Tour_typeRestController {
    @Autowired
    Tour_typeDAO tour_typeDAO;

    @GetMapping
    public ResponseEntity<List<Tour_type>> findAll() {
        return ResponseEntity.ok(tour_typeDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Tour_type> findById(@PathVariable("id") double tt_id) {
        Optional<Tour_type> optional = tour_typeDAO.findById(tt_id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @PostMapping()
    public ResponseEntity<Tour_type> post(@RequestBody Tour_type tour_type) {
        if (tour_typeDAO.existsById(tour_type.getTt_id())) {
            return ResponseEntity.badRequest().build();
        }
        tour_typeDAO.save(tour_type);
        return ResponseEntity.ok(tour_type);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tour_type> put(@PathVariable("id") double tt_id, @RequestBody Tour_type tour_type) {
        if (!tour_typeDAO.existsById(tt_id)) {
            return ResponseEntity.notFound().build();
        }
        tour_typeDAO.save(tour_type);
        return ResponseEntity.ok(tour_type);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") double tt_id) {
        if (!tour_typeDAO.existsById(tt_id)) {
            return ResponseEntity.notFound().build();
        }
        tour_typeDAO.deleteById(tt_id);
        return ResponseEntity.ok().build();
    }
}
