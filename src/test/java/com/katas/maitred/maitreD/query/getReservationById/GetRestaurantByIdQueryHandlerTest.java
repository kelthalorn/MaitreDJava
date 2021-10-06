package com.katas.maitred.maitreD.query.getReservationById;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.infrastructure.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetRestaurantByIdQueryHandlerTest {

    @Mock
    RestaurantRepository restaurantRepository;

    @Test
    void shouldReturnRestaurantWhenReceivingItsId() {
        Integer restaurantId = 1;
        String name = "boutique";
        Map<String, Integer> dailyRemainingSeat = new HashMap<>();
        dailyRemainingSeat.put("2021-01-01", 10);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());

        Restaurant expectedRestaurant = new Restaurant(restaurantId, name, dailyRemainingSeat, reservations);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(expectedRestaurant));

        GetRestaurantByIdQueryHandler handler = new GetRestaurantByIdQueryHandler(restaurantRepository);
        Restaurant restaurant = handler.handle(restaurantId);

        assertEquals(restaurant, expectedRestaurant);
    }
}