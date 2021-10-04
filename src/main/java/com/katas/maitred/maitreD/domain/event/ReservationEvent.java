package com.katas.maitred.maitreD.domain.event;

import com.katas.maitred.maitreD.domain.ReservationType;

import javax.persistence.Entity;

@Entity
public class ReservationEvent extends Event{
    private ReservationType type;
    private Integer restaurantId;
    private Integer customerId;
    private Integer quantity;
    private String date;

    public ReservationEvent() {
    }

    public ReservationEvent(ReservationType type, Integer restaurantId, Integer customerId, Integer quantity, String date) {
        this.type = type;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.date = date;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
