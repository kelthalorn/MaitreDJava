package com.katas.maitred.maitreD.domain;

import com.katas.maitred.maitreD.domain.event.RestaurantEvent;
import com.katas.maitred.maitreD.domain.event.RestaurantEventType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Restaurant {

    public static final Integer MAX_GUEST_AT_TABLE = 12;

    @Id
    private Integer id;
    private String name;

    @ElementCollection
    private Map<String, Integer> dailyRemainingSeat = new HashMap<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(Integer id, String name, Map<String, Integer> dailyRemainingSeat, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.dailyRemainingSeat = dailyRemainingSeat;
        this.reservations = reservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getDailyRemainingSeat() {
        return dailyRemainingSeat;
    }

    public void setDailyRemainingSeat(Map<String, Integer> dailyRemainingSeat) {
        this.dailyRemainingSeat = dailyRemainingSeat;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public RestaurantEvent toEvent(RestaurantEventType restaurantEventType) {
        Map<String, Integer> newDailyRemainingSeat = new HashMap<>();
        newDailyRemainingSeat.putAll(this.getDailyRemainingSeat());
        return new RestaurantEvent(
                restaurantEventType,
                this.id,
                this.name
        );
    }

    public static Restaurant fromEvent(RestaurantEvent restaurantEvent) {
        return new Restaurant(
                restaurantEvent.getRestaurantId(),
                restaurantEvent.getName()
        );
    }
}
