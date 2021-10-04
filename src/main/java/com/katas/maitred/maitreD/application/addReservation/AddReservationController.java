package com.katas.maitred.maitreD.application.addReservation;

import com.katas.maitred.maitreD.command.addReservation.AddReservationCommandHandler;
import com.katas.maitred.maitreD.command.confirmReservation.ConfirmReservationCommandHandler;
import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.command.addReservation.CreateReservation;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.domain.exception.RestaurantNotFound;
import com.katas.maitred.maitreD.query.getReservationById.GetReservationByIdQueryHandler;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddReservationController {

    private final AddReservationCommandHandler addReservationCommandHandler;
    private final ConfirmReservationCommandHandler confirmReservationCommandHandler;
    private final GetReservationByIdQueryHandler getReservationByIdQueryHandler;

    public AddReservationController(AddReservationCommandHandler addReservationCommandHandler,
                                    ConfirmReservationCommandHandler confirmReservationCommandHandler,
                                    GetReservationByIdQueryHandler getReservationByIdQueryHandler) {
        this.addReservationCommandHandler = addReservationCommandHandler;
        this.confirmReservationCommandHandler = confirmReservationCommandHandler;
        this.getReservationByIdQueryHandler = getReservationByIdQueryHandler;
    }

    @GetMapping("/reservations/{id}")
    public Reservation getReservation(@PathVariable Integer id) {
        return getReservationByIdQueryHandler.handle(id);
    }

    @PostMapping("/reservation")
    public ReservationEvent createReservation(@RequestBody CreateReservation createReservation) {
        return addReservationCommandHandler.handle(createReservation);
    }

    @PostMapping("/reservation/{eventId}/confirm")
    public ReservationEvent confirmReservation(@PathVariable Integer eventId) throws RestaurantNotFound {
        return confirmReservationCommandHandler.handle(eventId);
    }
}
