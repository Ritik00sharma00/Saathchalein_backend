package com.saathchalein_backend.saathchalein_backend.service;

import com.saathchalein_backend.saathchalein_backend.dbo.TripDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.TripDBORepository;
import com.saathchalein_backend.saathchalein_backend.dto.TripCreateRequest;
import com.saathchalein_backend.saathchalein_backend.dto.TripResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    private final TripDBORepository tripDBORepository;

    public TripService(TripDBORepository tripDBORepository) {
        this.tripDBORepository = tripDBORepository;
    }

    public TripDBO createTrip(Long createdByUserId, TripCreateRequest request) {
        TripDBO trip = new TripDBO();
        trip.setCreatedByUserId(createdByUserId);
        trip.setTripName(request.getTripName());
        trip.setTripGroup(request.getTripGroup());
        trip.setMaxMembers(request.getMaxMembers());
        trip.setTripPlanned(request.getTripPlanned());
        trip.setActive(true);
        trip.setTripLock(false);
        return tripDBORepository.save(trip);
    }

    public List<TripResponse> getAllTrips() {
        return tripDBORepository.findAll().stream()
                .map(trip -> new TripResponse(
                        trip.getId(),
                        trip.getCreatedByUserId(),
                        trip.getTripName(),
                        trip.getTripGroup(),
                        trip.getMaxMembers(),
                        trip.isActive(),
                        trip.isTripLock(),
                        trip.getTripPlanned()
                ))
                .collect(Collectors.toList());
    }

    public TripDBO getTripById(Long tripId) {
        return tripDBORepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + tripId));
    }

    public List<TripDBO> getTripsByUser(Long userId) {
        return tripDBORepository.findByCreatedByUserId(userId);
    }
}
