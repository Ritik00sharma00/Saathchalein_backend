package com.saathchalein_backend.saathchalein_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripCreateRequest {
    @NotBlank(message = "Trip name is required")
    private String tripName;

    private String tripGroup;

    @Min(value = 1, message = "Max members must be at least 1")
    private int maxMembers;

    private LocalDateTime tripPlanned;
}
