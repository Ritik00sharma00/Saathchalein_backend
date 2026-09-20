package com.saathchalein_backend.saathchalein_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDecisionRequest {
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "(?i)ACCEPTED|PENDING|REJECTED", message = "Status must be ACCEPTED, PENDING or REJECTED")
    private String status;
}
