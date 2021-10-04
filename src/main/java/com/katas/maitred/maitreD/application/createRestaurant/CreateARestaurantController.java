package com.katas.maitred.maitreD.application.createRestaurant;

import com.katas.maitred.maitreD.command.addRestaurant.AddRestaurantCommandHandler;
import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.command.addRestaurant.CreateRestaurant;
import com.katas.maitred.maitreD.query.getReservationById.GetRestaurantByIdQueryHandler;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateARestaurantController {

    private final AddRestaurantCommandHandler addRestaurantCommandHandler;
    private final GetRestaurantByIdQueryHandler getRestaurantByIdQueryHandler;

    public CreateARestaurantController(AddRestaurantCommandHandler addRestaurantCommandHandler,
                                       GetRestaurantByIdQueryHandler getRestaurantByIdQueryHandler) {
        this.addRestaurantCommandHandler = addRestaurantCommandHandler;
        this.getRestaurantByIdQueryHandler = getRestaurantByIdQueryHandler;
    }

    @PostMapping("/restaurant")
    public Restaurant createRestaurant(@RequestBody CreateRestaurant createRestaurant) {
        return addRestaurantCommandHandler.handle(createRestaurant);
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getRestaurant(@PathVariable Integer id) {
        return getRestaurantByIdQueryHandler.handle(id);
    }


}
