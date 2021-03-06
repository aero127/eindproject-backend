package nl.mtbrental.eindproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bike {


    @Column
    private String bikeName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantityTotal;

    @Column
    private Long pricePerDay;


    @JsonIgnore
    @OneToMany(mappedBy = "bike")
    List<Booking> bookings;

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(int quantityTotal) {
        this.quantityTotal = quantityTotal;
    }


}
