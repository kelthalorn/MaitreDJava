package com.katas.maitred.maitreD.command.addRestaurant;

import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.domain.event.RestaurantEvent;
import com.katas.maitred.maitreD.domain.event.RestaurantEventType;
import com.katas.maitred.maitreD.infrastructure.RestaurantEventRepository;
import com.katas.maitred.maitreD.infrastructure.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddRestaurantCommandHandlerTest {

    @Mock
    RestaurantEventRepository restaurantEventRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @Test
    void shouldReturnARestaurantWhenReceiveACreateRestaurantCommand() {

        CreateRestaurant createRestaurant = mock(CreateRestaurant.class);
        Restaurant expectedRestaurant = mock(Restaurant.class);
        RestaurantEvent restaurantEvent = mock(RestaurantEvent.class);

        when(createRestaurant.getRestaurant()).thenReturn(expectedRestaurant);
        when(expectedRestaurant.toEvent(RestaurantEventType.RESTAURANT_ADDED)).thenReturn(restaurantEvent);

        AddRestaurantCommandHandler handler = new AddRestaurantCommandHandler(
                restaurantRepository,
                restaurantEventRepository);

        Restaurant restaurant = handler.handle(createRestaurant);
        assertEquals(restaurant, expectedRestaurant);
    }
}