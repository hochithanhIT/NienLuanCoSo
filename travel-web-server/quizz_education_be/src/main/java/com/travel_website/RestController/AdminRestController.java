package com.travel_website.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.travel_website.DAO.AdminDAO;
import com.travel_website.Entity.Admin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dulich/api/admin")
public class AdminRestController {

    @Autowired
    AdminDAO adminDAO;

    @GetMapping
    public ResponseEntity<List<Admin>> findAll() {
        return ResponseEntity.ok(adminDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Admin> findById(@PathVariable("id") String Username){
       Optional<Admin> optional = adminDAO.findById(Username);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin loginAdmin) {
        if (loginAdmin.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username must not be null!");
        }
        Optional<Admin> optionalAdmin = adminDAO.findById(loginAdmin.getUsername());
        Admin admin = optionalAdmin.orElse(null); // hoặc sử dụng optionalUser.orElseThrow() nếu bạn muốn ném ngoại lệ khi không tìm thấy user
        
        if (admin == null || !admin.getUsername().equals(loginAdmin.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Tạo khóa ký an toàn cho thuật toán HS512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Sử dụng khóa ký để tạo JWT
        String token = Jwts.builder()
                            .setSubject(admin.getUsername())
                            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                            .signWith(key)
                            .compact();
        
        return ResponseEntity.ok(token);
    }

    @PostMapping()
    public ResponseEntity<Admin> post(@RequestBody Admin admin){
        if(adminDAO.existsById(admin.getUsername())){
            return ResponseEntity.badRequest().build();
        }
        adminDAO.save(admin);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("{id}")
    public ResponseEntity<Admin> put(@PathVariable("id") String idKichThuoc, @RequestBody Admin admin){
        if(!adminDAO.existsById(idKichThuoc)){
            return ResponseEntity.notFound().build();
        }
        adminDAO.save(admin);
         return ResponseEntity.ok(admin);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String username){
       if(!adminDAO.existsById(username)){
            return ResponseEntity.notFound().build();
        }
        adminDAO.deleteById(username);
        return ResponseEntity.ok().build();
    }
}
