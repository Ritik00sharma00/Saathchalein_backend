package com.saathchalein_backend.saathchalein_backend.repository;

import com.saathchalein_backend.saathchalein_backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByCreatedByUserId(Long createdByUserId);
}
