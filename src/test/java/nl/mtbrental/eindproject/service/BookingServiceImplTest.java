package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.model.User;
import nl.mtbrental.eindproject.repository.BikeRepository;
import nl.mtbrental.eindproject.repository.BookingRepository;
import nl.mtbrental.eindproject.repository.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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


    @Test
    public void getBookingByIdTest() {
        Booking booking = new Booking();
        booking.setId(1L);
        Long id = booking.getId();
        when(bookingRepository.getById(id)).thenReturn(booking);

        var booking1 = bookingService.getBooking(1L);
        assertThat(booking1.getId()).isEqualTo(1L);
    }

//    @Test
//    public void getAllBookingsByUsernameTest() {
//
//        var ListBooking = new ArrayList<>();
//        Bike bike = new Bike();
//        bike.setId(1L);
//        when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));
//
//        User testuser = new User();
//        testuser.setUsername("testuser");
//
//        User testuser2 = new User();
//        testuser2.setUsername("testuser2");
//
//        Booking booking1 = new Booking();
//        booking1.setUser(testuser);
//
//        Booking booking2 = new Booking();
//        booking2.setUser(testuser);
//
//        Booking booking3 = new Booking();
//        booking3.setUser(testuser2);
//
//        ListBooking.add(booking1);
//        ListBooking.add(booking2);
//        ListBooking.add(booking3);
//
//        when(userRepository.findById("testuser")).thenReturn(Optional.of(testuser));
//        bookingService.getBookingsByUsername("testuser");
//
//
//        when(bookingRepository.findBookingByUser(testuser)).thenReturn(List.of(booking1, booking2, booking3));
//        List<Booking> bookingList =  bookingService.getBookings();
//        assertThat(ListBooking).isEqualTo(2);
//    }


    @Test
    public void saveBookingTest() {
        Booking booking = new Booking();
        booking.setId(1000L);
        booking.setAmount(1);

        Bike bike = new Bike();
        bike.setId(1L);
        bike.setPricePerDay(55L);
        bike.setQuantityTotal(200);
        when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));

        User user = new User();
        user.setUsername("testuser");
        when(userRepository.findById("testuser")).thenReturn(Optional.of(user));

        bookingService.saveBooking(booking, 1L, "testuser");

        verify(bookingRepository).save(bookingCaptor.capture());

        booking = bookingCaptor.getValue();
        assertThat(booking.getUser()).isEqualTo(user);

    }

}
