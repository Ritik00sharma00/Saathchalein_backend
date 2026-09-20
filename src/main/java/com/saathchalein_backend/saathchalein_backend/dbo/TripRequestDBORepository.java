package com.saathchalein_backend.saathchalein_backend.dbo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRequestDBORepository extends JpaRepository<TripRequestDBO, Long> {
    List<TripRequestDBO> findByTripId(Long tripId);
    List<TripRequestDBO> findByRequestedByUserId(Long requestedByUserId);
    List<TripRequestDBO> findByStatus(TripRequestStatusDBO status);
}
