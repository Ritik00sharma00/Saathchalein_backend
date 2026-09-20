package com.saathchalein_backend.saathchalein_backend.service;

import com.saathchalein_backend.saathchalein_backend.dbo.TripRequestDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.TripRequestDBORepository;
import com.saathchalein_backend.saathchalein_backend.dbo.TripRequestStatusDBO;
import com.saathchalein_backend.saathchalein_backend.dto.TripRequestCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final TripRequestDBORepository tripRequestDBORepository;

    public RequestService(TripRequestDBORepository tripRequestDBORepository) {
        this.tripRequestDBORepository = tripRequestDBORepository;
    }

    public TripRequestDBO createRequest(TripRequestCreateRequest request, Long requestedByUserId) {
        TripRequestDBO tripRequest = new TripRequestDBO();
        tripRequest.setTripId(request.getTripId());
        tripRequest.setRequestedByUserId(requestedByUserId);
        tripRequest.setRequiredId(request.getRequiredId());
        tripRequest.setStatus(TripRequestStatusDBO.PENDING);
        return tripRequestDBORepository.save(tripRequest);
    }

    public List<TripRequestDBO> getRequestsByTrip(Long tripId) {
        return tripRequestDBORepository.findByTripId(tripId);
    }

    public List<TripRequestDBO> getRequestsByUser(Long userId) {
        return tripRequestDBORepository.findByRequestedByUserId(userId);
    }

    public TripRequestDBO updateStatus(Long requestId, String status) {
        TripRequestDBO request = tripRequestDBORepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with id: " + requestId));

        request.setStatus(TripRequestStatusDBO.valueOf(status.toUpperCase()));
        request.setUpdatedAt(java.time.LocalDateTime.now());
        return tripRequestDBORepository.save(request);
    }
}
