package com.travel_website.Entity;

import java.io.Serializable;
// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

// import javax.persistence.CascadeType;
// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "US_Name")
    private String us_name;

    @Column(name = "US_Password")
    private String us_password;

    @Column(name = "US_Fullname")
    private String us_fullname;

    @Column(name = "US_Gender")
    private boolean us_gender;

    @Column(name = "US_Email")
    private String us_email;

    @Column(name = "US_Phonenumber")
    private String us_phonenumber;

    @Column(name = "US_Address")
    private String us_address;

    // @JsonIgnore
    // @OneToMany(mappedBy = "B_detail_id.accountUser", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Booking> booking_detail;
    
}