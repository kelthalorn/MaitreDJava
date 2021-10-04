package com.katas.maitred.maitreD.command.addReservation;

import com.katas.maitred.maitreD.domain.Reservation;

public class CreateReservation {
    private Reservation reservation;

    public CreateReservation() {
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
