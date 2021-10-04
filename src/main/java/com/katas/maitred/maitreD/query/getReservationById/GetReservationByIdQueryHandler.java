package com.katas.maitred.maitreD.query.getReservationById;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.infrastructure.ReservationEventRepository;

public class GetReservationByIdQueryHandler {

    private final ReservationEventRepository reservationEventRepository;

    public GetReservationByIdQueryHandler(ReservationEventRepository reservationEventRepository) {
        this.reservationEventRepository = reservationEventRepository;
    }

    public Reservation handle(Integer id) {
        ReservationEvent reservationEvent = reservationEventRepository.findById(id).get();
        return Reservation.fromEvent(reservationEvent);
    }
}
