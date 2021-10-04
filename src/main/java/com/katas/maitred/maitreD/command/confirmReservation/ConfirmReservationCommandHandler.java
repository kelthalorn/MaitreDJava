package com.katas.maitred.maitreD.command.confirmReservation;

import com.katas.maitred.maitreD.domain.Reservation;
import com.katas.maitred.maitreD.domain.Restaurant;
import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import com.katas.maitred.maitreD.domain.ReservationType;
import com.katas.maitred.maitreD.domain.event.RestaurantEventType;
import com.katas.maitred.maitreD.domain.exception.RestaurantNotFound;
import com.katas.maitred.maitreD.infrastructure.ReservationEventRepository;
import com.katas.maitred.maitreD.infrastructure.RestaurantEventRepository;
import com.katas.maitred.maitreD.infrastructure.RestaurantRepository;

public class ConfirmReservationCommandHandler {

    private final ReservationEventRepository reservationEventRepository;
    private final RestaurantEventRepository restaurantEventRepository;
    private final RestaurantRepository restaurantRepository;

    public ConfirmReservationCommandHandler(ReservationEventRepository reservationEventRepository,
                                            RestaurantEventRepository restaurantEventRepository, RestaurantRepository restaurantRepository) {
        this.reservationEventRepository = reservationEventRepository;
        this.restaurantEventRepository = restaurantEventRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ReservationEvent handle(Integer eventId) throws RestaurantNotFound {
        ReservationEvent pendingReservationEvent = reservationEventRepository.findById(eventId).get();
        Reservation pendingReservation = Reservation.fromEvent(pendingReservationEvent);

        if (pendingReservation.getStatus() == ReservationType.RESERVATION_PENDING) {
            Restaurant restaurant = restaurantRepository.findById(pendingReservationEvent.getRestaurantId()).get();

            String reservationDate = pendingReservation.getDate();
            Integer remainingSeat = searchForRemainingSeatAtDate(restaurant, reservationDate);
            if (remainingSeat >= pendingReservation.getQuantity()) {
                pendingReservation.setStatus(ReservationType.RESERVATION_CONFIRMED);

                addReservationToRestaurant(restaurant, pendingReservation);

            } else {
                pendingReservation.setStatus(ReservationType.RESERVATION_REJECTED);
            }

            ReservationEvent event = pendingReservation.toEvent();
            reservationEventRepository.save(event);
            return event;
        }

        throw new RestaurantNotFound();
    }

    private Integer searchForRemainingSeatAtDate(Restaurant restaurant, String reservationDate) {
        if (!restaurant.getDailyRemainingSeat().containsKey(reservationDate)) {
            restaurant.getDailyRemainingSeat().put(reservationDate, Restaurant.MAX_GUEST_AT_TABLE);
        }
        return restaurant.getDailyRemainingSeat().get(reservationDate);
    }

    private void addReservationToRestaurant(Restaurant restaurant, Reservation pendingReservation) {
        restaurant.getReservations().add(pendingReservation);
        restaurant.getDailyRemainingSeat().replace(pendingReservation.getDate(),
                restaurant.getDailyRemainingSeat().get(pendingReservation.getDate()) - pendingReservation.getQuantity());
        restaurantRepository.save(restaurant);
        restaurantEventRepository.save(restaurant.toEvent(RestaurantEventType.RESTAURANT_RESERVATION_ADDED));
    }
}
