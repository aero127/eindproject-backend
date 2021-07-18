package nl.mtbrental.eindproject.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;

import java.time.LocalDateTime;
import java.util.Date;

public class BookingDto {

    @JsonSerialize
    Long id;

    @JsonSerialize
    BikeDto bike;

    @JsonSerialize
    UserDto user;

    @JsonSerialize
    String startTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime date;

    @JsonSerialize
    Long price;




    public static BookingDto fromBooking(Booking booking) {
        var dto = new BookingDto();
        dto.id = booking.getId();
        dto.bike = BikeDto.fromBike(booking.getBike());
        dto.user = UserDto.fromUser(booking.getUser());
        dto.startTime = booking.getStartTime();
        dto.date = booking.getDate();
        dto.price = booking.getPrice();

        return dto;
    }

}
