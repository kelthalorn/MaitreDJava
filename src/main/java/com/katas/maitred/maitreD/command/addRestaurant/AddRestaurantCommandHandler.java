package com.katas.maitred.maitreD.command.addRestaurant;

import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.domain.event.RestaurantEventType;
import com.katas.maitred.maitreD.infrastructure.RestaurantEventRepository;
import com.katas.maitred.maitreD.infrastructure.RestaurantRepository;

public class AddRestaurantCommandHandler {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEventRepository restaurantEventRepository;

    public AddRestaurantCommandHandler(RestaurantRepository restaurantRepository,
                                       RestaurantEventRepository restaurantEventRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEventRepository = restaurantEventRepository;
    }

    public Restaurant handle(CreateRestaurant createRestaurant) {
        Restaurant restaurant = createRestaurant.getRestaurant();
        restaurantRepository.save(restaurant);
        restaurantEventRepository.save(restaurant.toEvent(RestaurantEventType.RESTAURANT_ADDED));
        return restaurant;
    }
}
