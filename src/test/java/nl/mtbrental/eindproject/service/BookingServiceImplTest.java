package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.model.User;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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


//    @Test
//    public void getBookingByIdTest() {
//        Booking booking = new Booking();
//        booking.setId(1L);
//        Long id = booking.getId();
//        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
//        Optional<Booking> bookingOptional = bookingService.getBooking(id);
//    }

//    @Test
//    public void getAllBookingsByUsernameTest() {
//    Bike bike = new Bike();
//    bike.setId(1L);
//    when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));
//
//    User user = new User();
//    user.setUsername("testuser");
//    when(bookingRepository.findByUser(user)).thenReturn((List<Booking>) user);
//
//    bookingService.getBookingsByUsername("testuser");
//
//    verify(bookingRepository).save(bookingCaptor.capture());
//
//    Booking booking = bookingCaptor.getValue();
//    assertThat(booking.getUser()).isEqualTo(user);
//    }

//    @Test
//    public void saveBookingTest() {
//        Booking booking = new Booking();
//        booking.setId(1L);
//        booking.setAmount(1);
//        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
//
//        Bike bike = new Bike();
//        bike.setId(1L);
//        bike.setPricePerDay(55L);
//        bike.setQuantityTotal(200);
//        when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));
//
//        User user = new User();
//        user.setUsername("testuser");
//        when(userRepository.findById("testuser")).thenReturn(Optional.of(user));
//
//        bookingService.saveBooking(booking, 1L, "testuser");
//
//        verify(bookingRepository).save(bookingCaptor.capture());
//
//        booking = bookingCaptor.getValue();
//        assertThat(booking.getUser()).isEqualTo(user);
//
//    }



}
