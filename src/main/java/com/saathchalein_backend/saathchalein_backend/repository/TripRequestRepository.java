package com.saathchalein_backend.saathchalein_backend.repository;

import com.saathchalein_backend.saathchalein_backend.entity.RequestStatus;
import com.saathchalein_backend.saathchalein_backend.entity.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRequestRepository extends JpaRepository<TripRequest, Long> {
    List<TripRequest> findByTripId(Long tripId);
    List<TripRequest> findByRequestedByUserId(Long requestedByUserId);
    List<TripRequest> findByStatus(RequestStatus status);
}
