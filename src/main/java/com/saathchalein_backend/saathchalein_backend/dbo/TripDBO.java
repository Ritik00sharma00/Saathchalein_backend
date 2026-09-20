package com.saathchalein_backend.saathchalein_backend.dbo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by_user_id", nullable = false)
    private Long createdByUserId;

    @Column(name = "trip_name", nullable = false)
    private String tripName;

    @Column(name = "trip_group")
    private String tripGroup;

    @Column(name = "max_members")
    private int maxMembers;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "trip_lock")
    private boolean tripLock = false;

    @Column(name = "trip_planned")
    private LocalDateTime tripPlanned;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
