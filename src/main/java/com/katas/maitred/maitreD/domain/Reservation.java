package com.katas.maitred.maitreD.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer restaurantId;
    private Integer customerId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private String date;
    private ReservationType status = ReservationType.RESERVATION_PENDING;
    private Integer quantity;

    public Reservation() {
    }

    public Reservation(Integer restaurantId, Integer customerId, String date, Integer quantity, ReservationType reservationType) {
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.date = date;
        this.quantity = quantity;
        this.status = reservationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReservationType getStatus() {
        return status;
    }

    public void setStatus(ReservationType status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ReservationEvent toEvent() {
        return new ReservationEvent(
                this.getStatus(),
                this.getRestaurantId(),
                this.getCustomerId(),
                this.getQuantity(),
                this.getDate()
        );
    }

    public static Reservation fromEvent(ReservationEvent event) {
        return new Reservation(
            event.getRestaurantId(),
            event.getCustomerId(),
            event.getDate(),
            event.getQuantity(),
            event.getType()
        );
    }
}
