package com.example.D288.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="excursions")
@Data
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Excursion_ID")
    private Long id;

    @Column(name="Excursion_Title")
    private String excursion_title;

    @Column(name="Excursion_Price")
    private BigDecimal excursion_price;

    @Column(name="Image_URL")
    private String image_URL;

    @Column(name="Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name="Last_Update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name="Vacation_ID", nullable = false)
    private Vacation vacation;

    @ManyToMany
    @JoinTable(name="excursion_cartitem",
            joinColumns=@JoinColumn(name="excursion_id"),
            inverseJoinColumns=@JoinColumn(name="cart_item_id"))
    private Set<CartItem> cartItems = new HashSet<>();


}