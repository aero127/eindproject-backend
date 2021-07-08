package nl.mtbrental.eindproject.dto;

import nl.mtbrental.eindproject.model.Booking;

import java.time.LocalDateTime;

public class BookingInputDto {
    public Long bikeId;

    public String startTime;

    public LocalDateTime date;

    public String username;

    public Booking toBooking() {
        var booking = new Booking();
        booking.setStartTime(startTime);
        booking.setDate(date);
        return booking;
    }
}
