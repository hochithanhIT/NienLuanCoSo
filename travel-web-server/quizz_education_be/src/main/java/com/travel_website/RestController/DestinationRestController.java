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

import com.travel_website.DAO.DestinationDAO;
import com.travel_website.Entity.Destination;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dulich/api/destination")
public class DestinationRestController {
    @Autowired
    DestinationDAO destinationDAO;

    @GetMapping
    public ResponseEntity<List<Destination>> findAll() {
        return ResponseEntity.ok(destinationDAO.findAll());
    }

     @GetMapping("{id}")
    public ResponseEntity<Destination> findById(@PathVariable("id") double des_id) {
        Optional<Destination> optional = destinationDAO.findById(des_id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @PostMapping()
    public ResponseEntity<Destination> post(@RequestBody Destination destination) {
        if (destinationDAO.existsById(destination.getDes_id())) {
            return ResponseEntity.badRequest().build();
        }
        destinationDAO.save(destination);
        return ResponseEntity.ok(destination);
    }

    @PutMapping("{id}")
    public ResponseEntity<Destination> put(@PathVariable("id") double des_id, @RequestBody Destination destination) {
        if (!destinationDAO.existsById(des_id)) {
            return ResponseEntity.notFound().build();
        }
        destinationDAO.save(destination);
        return ResponseEntity.ok(destination);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") double des_id) {
        if (!destinationDAO.existsById(des_id)) {
            return ResponseEntity.notFound().build();
        } 
        destinationDAO.deleteById(des_id);
        return ResponseEntity.ok().build();
    }
}
