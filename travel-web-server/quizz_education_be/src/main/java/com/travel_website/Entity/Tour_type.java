package com.travel_website.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour_type")
public class Tour_type {
    @Id
    @Column(name = "TT_id")
    private double tt_id;

    @Column(name = "TT_typename")
    private String tt_typename;

    @JsonIgnore
    @OneToMany(mappedBy = "tour_type")
    private List<Tour> tour;
}
