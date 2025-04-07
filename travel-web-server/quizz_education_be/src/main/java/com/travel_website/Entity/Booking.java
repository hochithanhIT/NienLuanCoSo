package com.travel_website.Entity;

import java.io.Serializable;
// import java.time.LocalDate;
import java.time.LocalDateTime;
// import java.util.Date;
// import java.util.List;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @Column(name = "booking_id")
    private String booking_id;

    @Column(name = "booking_time")
    private LocalDateTime booking_time;

    @Column(name = "booking_quantity")
    private int booking_quantity;

    @Column(name = "booking_totalmoney")
    private Double booking_totalMoney;

    @Column(name = "TOUR_ID")
    private String tour_id;

   @Column(name = "US_Name")
    private String us_name;

    // @JsonIgnore
    // @OneToMany(mappedBy = "B_detail_id.booking", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Booking> booking_detail;
}
