package nl.mtbrental.eindproject.controller;

import nl.mtbrental.eindproject.dto.BookingDto;
import nl.mtbrental.eindproject.dto.BookingInputDto;
import nl.mtbrental.eindproject.exceptions.BadRequestException;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Transactional
    @GetMapping
    public List<BookingDto> getBookings(@RequestParam(value = "username", required = false) String username,
                                        @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                        @RequestParam(value = "bikeId", required = false) Long bikeId) {
        var dtos = new ArrayList<BookingDto>();

        List<Booking> bookings;
        if (username != null && date == null && bikeId == null) {
            bookings = bookingService.getBookingsByUsername(username);

        } else if (date != null && username == null && bikeId == null) {
            bookings = bookingService.getBookingsOnDate(date);

        } else if (date == null && username == null && bikeId != null) {
            bookings = bookingService.getBookingsForBike(bikeId);

        } else if (date == null && username == null && bikeId == null) {
            bookings = bookingService.getBookings();

        } else {
            throw new BadRequestException();
        }

        for (Booking booking : bookings) {
            dtos.add(BookingDto.fromBooking(booking));
        }

        return dtos;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public BookingDto saveBooking(@RequestBody BookingInputDto dto) {
        var booking = bookingService.saveBooking(dto.toBooking(), dto.bikeId, dto.username);
        return BookingDto.fromBooking(booking);
    }


    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> removeBooking(@PathVariable("id") Long id) {
        bookingService.removeBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookingDto getBooking(@PathVariable("id") Long id) {
        var booking =  bookingService.getBooking(id);
        return BookingDto.fromBooking(booking);
    }
}
