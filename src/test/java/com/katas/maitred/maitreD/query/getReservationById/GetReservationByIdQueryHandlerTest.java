package com.katas.maitred.maitreD.query.getReservationById;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.ReservationType;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.infrastructure.ReservationEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetReservationByIdQueryHandlerTest {

    @Mock
    ReservationEventRepository reservationEventRepository;

    @Test
    void shouldReturnReservationwhenReceivingAnEventId(){
        Integer eventId = 1;
        Integer restaurantId = 1234;
        Integer customerId = 2;
        Integer quantity = 10;
        String reservationDate = "2021-01-01";


        ReservationEvent reservationEvent = mock(ReservationEvent.class);
        when(reservationEvent.getRestaurantId()).thenReturn(restaurantId);
        when(reservationEvent.getCustomerId()).thenReturn(customerId);
        when(reservationEvent.getDate()).thenReturn(reservationDate);
        when(reservationEvent.getQuantity()).thenReturn(quantity);
        when(reservationEvent.getType()).thenReturn(ReservationType.RESERVATION_PENDING);

        when(reservationEventRepository.findById(eventId)).thenReturn(Optional.of(reservationEvent));

        GetReservationByIdQueryHandler handler = new GetReservationByIdQueryHandler(reservationEventRepository);

        Reservation reservation = handler.handle(eventId);

        assertEquals(reservation.getStatus(), reservationEvent.getType());
        assertEquals(reservation.getRestaurantId(), reservationEvent.getRestaurantId());
        assertEquals(reservation.getCustomerId(), reservationEvent.getCustomerId());
        assertEquals(reservation.getQuantity(), reservationEvent.getQuantity());
        assertEquals(reservation.getQuantity(), reservationEvent.getQuantity());
        assertEquals(reservation.getDate(), reservationEvent.getDate());

    }
}