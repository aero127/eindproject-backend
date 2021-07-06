package nl.mtbrental.eindproject.controller;



import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) { this.bookingService = bookingService; }

    @GetMapping("")
    public ResponseEntity<Object> getBookings() {return ResponseEntity.ok(bookingService.getBookings());}

    @PostMapping("")
    public ResponseEntity<Object> addBooking(@RequestBody Booking booking) {
        bookingService.addBooking(booking);
        return ResponseEntity.ok("added");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookings(@PathVariable("id") long id) {
        Booking booking = (Booking) bookingService.getBookings(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> removeBooking(@PathVariable("id") long id) {
        bookingService.removeBooking(id);
        return ResponseEntity.noContent().build();
    }

}

