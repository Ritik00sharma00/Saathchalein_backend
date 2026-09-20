package com.saathchalein_backend.saathchalein_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {
    private Long id;
    private Long createdByUserId;
    private String tripName;
    private String tripGroup;
    private int maxMembers;
    private boolean active;
    private boolean tripLock;
    private LocalDateTime tripPlanned;
}
