package com.katas.maitred.maitreD.infrastructure;

import com.katas.maitred.maitreD.domain.event.ReservationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationEventRepository extends JpaRepository<ReservationEvent, Integer> {
}
