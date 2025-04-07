package com.travel_website.Entity;

import java.sql.Date;
// import java.util.List;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @Column(name = "TOUR_id")
    private String tour_id;

    @Column(name = "TOUR_title")
    private String tour_title;

    @Column(name = "TOUR_description")
    private String tour_description;

    @Column(name = "TOUR_day")
    private String tour_day;

    @Column(name = "TOUR_slot")
    private int tour_slot;

    @Column(name = "TOUR_departureday")
    private Date tour_departureDay;

    @Column(name = "TOUR_backday")
    private Date tour_backDay;

    @Column(name = "TOUR_unitprice")
    private Double tour_unitPrice;

    @Column(name = "TOUR_image")
    private String tour_image;

    @ManyToOne
    @JoinColumn(name = "TT_ID")
    private Tour_type tour_type;

    @ManyToOne
    @JoinColumn(name = "DES_ID")
    private Destination destination;

    // @JsonIgnore
    // @OneToMany(mappedBy = "B_detail_id.tour", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Booking> booking_detail;
}