package com.katas.maitred.maitreD.command.confirmReservation;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.ReservationType;
import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.domain.exception.RestaurantNotFound;
import com.katas.maitred.maitreD.infrastructure.ReservationEventRepository;
import com.katas.maitred.maitreD.infrastructure.RestaurantEventRepository;
import com.katas.maitred.maitreD.infrastructure.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConfirmReservationCommandHandlerTest {

    @Mock
    ReservationEventRepository reservationEventRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    RestaurantEventRepository restaurantEventRepository;

    @Test
    void shouldReturnReservationEventForConfirmationWhenReceivedAnEventIdWithReservationPossible() throws RestaurantNotFound {

        Integer eventId = 1;
        Integer restaurantId = 1;
        String reservationDate = "2021-01-01";
        Integer quantity = 1;
        Integer remainingSeat = 10;

        ReservationEvent pendingReservationEvent = mock(ReservationEvent.class);
        when(pendingReservationEvent.getRestaurantId()).thenReturn(restaurantId);
        when(pendingReservationEvent.getCustomerId()).thenReturn(1);
        when(pendingReservationEvent.getDate()).thenReturn(reservationDate);
        when(pendingReservationEvent.getQuantity()).thenReturn(quantity);
        when(pendingReservationEvent.getType()).thenReturn(ReservationType.RESERVATION_PENDING);

        when(reservationEventRepository.findById(eventId)).thenReturn(Optional.of(pendingReservationEvent));

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        Map<String, Integer> dailyRemainingSeat = new HashMap<>();
        dailyRemainingSeat.put(reservationDate, remainingSeat);
        when(restaurant.getDailyRemainingSeat()).thenReturn(dailyRemainingSeat);

        List<Reservation> reservations = new ArrayList<>();
        when(restaurant.getReservations()).thenReturn(reservations);

        ConfirmReservationCommandHandler handler = new ConfirmReservationCommandHandler(
                reservationEventRepository,
                restaurantEventRepository,
                restaurantRepository);


        ReservationEvent event = handler.handle(eventId);
        assertEquals(event.getType(), ReservationType.RESERVATION_CONFIRMED);
    }

    @Test
    void shouldReturnReservationEventForConfirmationWhenReceivedAnEventIdWithReservationImPossible() throws RestaurantNotFound {

        Integer eventId = 1;
        Integer restaurantId = 1;
        String reservationDate = "2021-01-01";
        Integer quantity = 2;
        Integer remainingSeat = 1;

        ReservationEvent pendingReservationEvent = mock(ReservationEvent.class);
        when(pendingReservationEvent.getRestaurantId()).thenReturn(restaurantId);
        when(pendingReservationEvent.getCustomerId()).thenReturn(1);
        when(pendingReservationEvent.getDate()).thenReturn(reservationDate);
        when(pendingReservationEvent.getQuantity()).thenReturn(quantity);
        when(pendingReservationEvent.getType()).thenReturn(ReservationType.RESERVATION_PENDING);

        when(reservationEventRepository.findById(eventId)).thenReturn(Optional.of(pendingReservationEvent));

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        Map<String, Integer> dailyRemainingSeat = new HashMap<>();
        dailyRemainingSeat.put(reservationDate, remainingSeat);
        when(restaurant.getDailyRemainingSeat()).thenReturn(dailyRemainingSeat);

        List<Reservation> reservations = new ArrayList<>();
        when(restaurant.getReservations()).thenReturn(reservations);

        ConfirmReservationCommandHandler handler = new ConfirmReservationCommandHandler(
                reservationEventRepository,
                restaurantEventRepository,
                restaurantRepository);


        ReservationEvent event = handler.handle(eventId);
        assertEquals(event.getType(), ReservationType.RESERVATION_REJECTED);
    }

    @Test
    void shouldReturnReservationEventForConfirmationWhenReceivedAnEventIdWithReservationPossibleForNewDate() throws RestaurantNotFound {

        Integer eventId = 1;
        Integer restaurantId = 1;
        String reservationDate1 = "2021-01-01";
        String currentReservationDate = "2021-01-02";
        Integer quantity = 2;
        Integer remainingSeat = 1;

        ReservationEvent pendingReservationEvent = mock(ReservationEvent.class);
        when(pendingReservationEvent.getRestaurantId()).thenReturn(restaurantId);
        when(pendingReservationEvent.getCustomerId()).thenReturn(1);
        when(pendingReservationEvent.getDate()).thenReturn(currentReservationDate);
        when(pendingReservationEvent.getQuantity()).thenReturn(quantity);
        when(pendingReservationEvent.getType()).thenReturn(ReservationType.RESERVATION_PENDING);

        when(reservationEventRepository.findById(eventId)).thenReturn(Optional.of(pendingReservationEvent));

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        Map<String, Integer> dailyRemainingSeat = new HashMap<>();
        dailyRemainingSeat.put(reservationDate1, remainingSeat);
        when(restaurant.getDailyRemainingSeat()).thenReturn(dailyRemainingSeat);

        List<Reservation> reservations = new ArrayList<>();
        when(restaurant.getReservations()).thenReturn(reservations);

        ConfirmReservationCommandHandler handler = new ConfirmReservationCommandHandler(
                reservationEventRepository,
                restaurantEventRepository,
                restaurantRepository);


        ReservationEvent event = handler.handle(eventId);
        assertEquals(event.getType(), ReservationType.RESERVATION_CONFIRMED);
    }
}