package com.saathchalein_backend.saathchalein_backend.controller;

import com.saathchalein_backend.saathchalein_backend.dbo.TripDBO;
import com.saathchalein_backend.saathchalein_backend.dto.TripCreateRequest;
import com.saathchalein_backend.saathchalein_backend.dto.TripResponse;
import com.saathchalein_backend.saathchalein_backend.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<TripDBO> createTrip(@RequestParam Long userId,
                                            @Valid @RequestBody TripCreateRequest request) {
        return ResponseEntity.ok(tripService.createTrip(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<TripResponse>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDBO> getTripById(@PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.getTripById(tripId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TripDBO>> getTripsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(tripService.getTripsByUser(userId));
    }
}
