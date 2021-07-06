package nl.mtbrental.eindproject.model;


import javax.persistence.*;

@Entity
public class Bike {

    //attributen

    @Column
    private String bikeName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int quantity;

    @Column
    private boolean isAvailable;

//    @Column
//    private boolean spdPedals;


    //getters & setters
    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    //    public boolean isHelmet() {
//        return helmet;
//    }
//
//    public void setHelmet(boolean helmet) {
//        this.helmet = helmet;
//    }
//
//    public boolean isSpdPedals() {
//        return spdPedals;
//    }
//
//    public void setSpdPedals(boolean spdPedals) {
//        this.spdPedals = spdPedals;
//    }
}
