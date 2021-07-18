package nl.mtbrental.eindproject.dto;

import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.model.User;

import java.time.LocalDateTime;

public class BookingInputDto {
    public Long bikeId;

    public String startTime;

    public LocalDateTime date;

    public String username;

    public Long price;

//    public Long quantityTotal;

    public float amount;

    public boolean helmet;

    public boolean spdPedals;

    public Booking toBooking() {
        var booking = new Booking();
        booking.setStartTime(startTime);
        booking.setDate(date);
        booking.setPrice(price);
      //  booking.setQuantityTotal(quantityTotal);
        booking.setAmount(amount);
        booking.setHelmet(helmet);
        booking.setSpdPedals(spdPedals);
        return booking;
    }
}
