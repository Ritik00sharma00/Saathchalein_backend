package com.saathchalein_backend.saathchalein_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestCreateRequest {
    @NotNull(message = "Trip id is required")
    private Long tripId;

    private Long requiredId;
}
