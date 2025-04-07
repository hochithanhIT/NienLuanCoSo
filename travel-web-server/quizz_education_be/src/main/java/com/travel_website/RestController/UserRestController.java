package com.travel_website.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.http.HttpStatus;
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

import com.travel_website.DAO.UserDAO;
// import com.travel_website.Entity.Tour;
import com.travel_website.Entity.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dulich/api/users")
public class UserRestController {
   
    @Autowired
    UserDAO userDAO;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userDAO.findAll());
    }

    
    @GetMapping("{us_name}")
    public ResponseEntity<User> findById(@PathVariable("us_name") String us_name) {
        Optional<User> optional = userDAO.findById(us_name);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        if (loginUser.getUs_name() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username must not be null!");
        }
        Optional<User> optionalUser = userDAO.findById(loginUser.getUs_name());
        User user = optionalUser.orElse(null); // hoặc sử dụng optionalUser.orElseThrow() nếu bạn muốn ném ngoại lệ khi không tìm thấy user
        
        if (user == null || !user.getUs_password().equals(loginUser.getUs_password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Tạo khóa ký an toàn cho thuật toán HS512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Sử dụng khóa ký để tạo JWT
        String token = Jwts.builder()
                            .setSubject(user.getUs_name())
                            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                            .signWith(key)
                            .compact();
        
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User newUser) {
        if (userDAO.existsById(newUser.getUs_name())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        userDAO.save(newUser);
        return ResponseEntity.ok(newUser);
    }

    // @PostMapping()
    // public ResponseEntity<User> post(@RequestBody User user) {
    //     if (userDAO.existsById(user.getUs_id())) {
    //         return ResponseEntity.badRequest().build();
    //     }
    //     userDAO.save(user);
    //     return ResponseEntity.ok(user);
    // }

    @PutMapping("{us_name}")
    public ResponseEntity<User> put(@PathVariable("us_name") String us_name, @RequestBody User user) {
        if (!userDAO.existsById(us_name)) {
            return ResponseEntity.notFound().build();
        }
        userDAO.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{us_name}")
    public ResponseEntity<Void> delete(@PathVariable("us_name") String us_name) {
        if (!userDAO.existsById(us_name)) {
            return ResponseEntity.notFound().build();
        }
        userDAO.deleteById(us_name);
        return ResponseEntity.ok().build();
    }
}

