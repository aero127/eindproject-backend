package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.repository.BikeRepository;
import nl.mtbrental.eindproject.repository.BookingRepository;
import nl.mtbrental.eindproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BikeRepository bikeRepository;

    @Mock
    BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Captor
    ArgumentCaptor<Booking> bookingCaptor;


    @Test
    public void getAllBookingsTest() {
        when(bookingRepository.findAll()).thenReturn(List.of(new Booking(), new Booking()));
        List<Booking> bookingList =  bookingService.getBookings();
        assertEquals(2, bookingList.size());
    }

//    public void getBookingByIdTest() {
//        Booking booking = new Booking();
//        booking.setId(1L);
//        Long id = booking.getId();
//        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
//        Optional<Booking> bookingOptional = bookingService.getBookings(id);
//    }

}
