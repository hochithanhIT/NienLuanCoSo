package com.travel_website.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "destination")
public class Destination {
    @Id
    @Column(name = "DES_ID")
    private double des_id;

    @Column(name = "DES_Name")
    private String des_name;

    @JsonIgnore
    @OneToMany(mappedBy = "destination")
    private List<Tour> tour;

}
