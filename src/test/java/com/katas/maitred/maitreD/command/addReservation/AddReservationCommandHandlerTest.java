package com.katas.maitred.maitreD.command.addReservation;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.infrastructure.ReservationEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddReservationCommandHandlerTest {

    @Mock
    ReservationEventRepository reservationEventRepository;

    @Test
    void shouldReturnAReservationEventWhenReceiveACreateReservationCommand() {
        CreateReservation createReservation= mock(CreateReservation.class);
        Reservation reservation = mock(Reservation.class);

        ReservationEvent expectedReservationEvent = mock(ReservationEvent.class);

        when(createReservation.getReservation()).thenReturn(reservation);
        when(reservation.toEvent()).thenReturn(expectedReservationEvent);

        AddReservationCommandHandler handler = new AddReservationCommandHandler(reservationEventRepository);
        ReservationEvent event = handler.handle(createReservation);
        assertEquals(event, expectedReservationEvent);
    }
}