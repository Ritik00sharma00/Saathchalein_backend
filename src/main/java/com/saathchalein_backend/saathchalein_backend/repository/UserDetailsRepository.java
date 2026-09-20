package com.saathchalein_backend.saathchalein_backend.repository;

import com.saathchalein_backend.saathchalein_backend.entity.User;
import com.saathchalein_backend.saathchalein_backend.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUser(User user);
}
