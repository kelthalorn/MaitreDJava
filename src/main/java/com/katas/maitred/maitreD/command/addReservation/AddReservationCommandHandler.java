package com.katas.maitred.maitreD.command.addReservation;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.infrastructure.ReservationEventRepository;

public class AddReservationCommandHandler {

    private final ReservationEventRepository reservationEventRepository;

    public AddReservationCommandHandler(ReservationEventRepository reservationEventRepository) {
        this.reservationEventRepository = reservationEventRepository;
    }

    public ReservationEvent handle(CreateReservation createReservation) {
        Reservation reservation = createReservation.getReservation();

        ReservationEvent event = reservation.toEvent();
        reservationEventRepository.save(event);
        return event;
    }
}
