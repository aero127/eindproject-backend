package nl.mtbrental.eindproject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long price;

    private LocalDateTime date;

    private int amount;

    @ManyToOne
    private Bike bike;

    @ManyToOne
    private User user;

    private String startTime;

    private boolean helmet;

    private boolean spdPedals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public boolean isSpdPedals() {
        return spdPedals;
    }

    public void setSpdPedals(boolean spdPedals) {
        this.spdPedals = spdPedals;
    }
}

