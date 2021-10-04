package com.katas.maitred.maitreD.infrastructure;

import com.katas.maitred.maitreD.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
