package com.katas.maitred.maitreD.infrastructure;

import com.katas.maitred.maitreD.domain.event.RestaurantEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantEventRepository extends JpaRepository<RestaurantEvent, Integer> {
}
