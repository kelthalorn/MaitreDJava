package com.katas.maitred.maitreD.query.getReservationById;

import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.infrastructure.RestaurantRepository;

public class GetRestaurantByIdQueryHandler {

    private final RestaurantRepository restaurantRepository;

    public GetRestaurantByIdQueryHandler(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant handle(Integer id) {
        return restaurantRepository.findById(id).get();
    }
}
