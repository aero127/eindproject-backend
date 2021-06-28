package com.mtbrental.eindproject.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    private Long id;

    private boolean availability;

    private int price;

    private String rentDuration;

    private int quantity;

    @ManyToOne
    private Bike bike;


}
