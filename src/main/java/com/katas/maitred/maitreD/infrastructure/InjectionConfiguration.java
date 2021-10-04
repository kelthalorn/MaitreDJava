package com.katas.maitred.maitreD.infrastructure;

import com.katas.maitred.maitreD.command.addReservation.AddReservationCommandHandler;
import com.katas.maitred.maitreD.command.addRestaurant.AddRestaurantCommandHandler;
import com.katas.maitred.maitreD.command.confirmReservation.ConfirmReservationCommandHandler;
import com.katas.maitred.maitreD.query.getReservationById.GetReservationByIdQueryHandler;
import com.katas.maitred.maitreD.query.getReservationById.GetRestaurantByIdQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectionConfiguration {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEventRepository restaurantEventRepository;
    private final ReservationEventRepository reservationEventRepository;

    public InjectionConfiguration(RestaurantRepository restaurantRepository,
                                  RestaurantEventRepository restaurantEventRepository,
                                  ReservationEventRepository reservationEventRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEventRepository = restaurantEventRepository;
        this.reservationEventRepository = reservationEventRepository;
    }

    @Bean
    public AddRestaurantCommandHandler restaurantCommandHandler() {
        return new AddRestaurantCommandHandler(restaurantRepository, restaurantEventRepository);
    }

    @Bean
    public AddReservationCommandHandler addReservationCommandHandler() {
        return new AddReservationCommandHandler(reservationEventRepository);
    }

    @Bean
    public GetReservationByIdQueryHandler getReservationByIdQueryHandler() {
        return new GetReservationByIdQueryHandler(reservationEventRepository);
    }

    @Bean
    public ConfirmReservationCommandHandler confirmReservationCommandHandler() {
        return new ConfirmReservationCommandHandler(reservationEventRepository, restaurantEventRepository, restaurantRepository);
    }

    @Bean
    public GetRestaurantByIdQueryHandler getRestaurantByIdQueryHandler() {
        return new GetRestaurantByIdQueryHandler(restaurantRepository);
    }


}
