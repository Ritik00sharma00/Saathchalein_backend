package com.saathchalein_backend.saathchalein_backend.controller;

import com.saathchalein_backend.saathchalein_backend.dbo.TripRequestDBO;
import com.saathchalein_backend.saathchalein_backend.dto.RequestDecisionRequest;
import com.saathchalein_backend.saathchalein_backend.dto.TripRequestCreateRequest;
import com.saathchalein_backend.saathchalein_backend.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<TripRequestDBO> createRequest(@RequestParam Long userId,
                                                      @Valid @RequestBody TripRequestCreateRequest request) {
        return ResponseEntity.ok(requestService.createRequest(request, userId));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<TripRequestDBO>> getRequestsByTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(requestService.getRequestsByTrip(tripId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TripRequestDBO>> getRequestsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByUser(userId));
    }

    @PatchMapping("/{requestId}/status")
    public ResponseEntity<TripRequestDBO> updateStatus(@PathVariable Long requestId,
                                                     @Valid @RequestBody RequestDecisionRequest request) {
        return ResponseEntity.ok(requestService.updateStatus(requestId, request.getStatus()));
    }
}
