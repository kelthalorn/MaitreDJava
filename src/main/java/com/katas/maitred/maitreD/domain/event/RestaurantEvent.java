package com.katas.maitred.maitreD.domain.event;

import javax.persistence.*;

@Entity
public class RestaurantEvent extends Event{

    private RestaurantEventType type;
    private Integer restaurantId;
    private String name;

    public RestaurantEvent() {
    }

    public RestaurantEvent(RestaurantEventType type, Integer restaurantId, String name) {
        this.type = type;
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public RestaurantEventType getType() {
        return type;
    }

    public void setType(RestaurantEventType type) {
        this.type = type;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
