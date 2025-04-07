package com.travel_website.Entity;

import java.io.Serializable;
// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin implements Serializable {

    @Id
    @Column(name = "AD_Useradmin")
    private String username;

    @Column(name = "AD_Password")
    private String password;

    @Column(name = "AD_State")
    private int state;

    // @JsonIgnore
    // @OneToMany(mappedBy = "accountUser")
    // private List<Booking_detail_id> booking_detail_id;
}
