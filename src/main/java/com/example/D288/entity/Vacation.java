package com.example.D288.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="vacations")
@Getter
@Setter
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Vacation_ID")
    private Long id;

    @Column(name="Vacation_Title")
    private String vacation_title;

    @Column(name="Description")
    private String description;

    @Column(name="Travel_Fare_Price")
    private BigDecimal travel_price;

    @Column(name="Image_URL")
    private String image_URL;

    @Column(name="Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name="Last_Update")
    @UpdateTimestamp
    private Date last_update;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "vacation")
    private Set<Excursion> excursions;
}