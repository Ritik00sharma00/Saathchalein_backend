package com.saathchalein_backend.saathchalein_backend.service;

import com.saathchalein_backend.saathchalein_backend.dbo.RoleDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDBORepository;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDetailsDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDetailsDBORepository;
import com.saathchalein_backend.saathchalein_backend.dto.UserDetailsRequest;
import com.saathchalein_backend.saathchalein_backend.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDBORepository userDBORepository;
    private final UserDetailsDBORepository userDetailsDBORepository;

    public UserService(UserDBORepository userDBORepository, UserDetailsDBORepository userDetailsDBORepository) {
        this.userDBORepository = userDBORepository;
        this.userDetailsDBORepository = userDetailsDBORepository;
    }

    public List<UserResponse> getAllUsers() {
        return userDBORepository.findAll().stream()
                .map(user -> {
                    Optional<UserDetailsDBO> details = userDetailsDBORepository.findByUser(user);
                    String role = details.map(d -> d.getRole().name()).orElse(RoleDBO.USER.name());
                    return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), role);
                })
                .collect(Collectors.toList());
    }

    public UserDBO getUserById(Long id) {
        return userDBORepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public UserDetailsDBO updateUserDetails(Long userId, UserDetailsRequest request) {
        UserDBO user = getUserById(userId);
        UserDetailsDBO userDetails = userDetailsDBORepository.findByUser(user)
                .orElseGet(() -> new UserDetailsDBO());

        userDetails.setUser(user);
        userDetails.setCity(request.getCity());
        userDetails.setPincode(request.getPincode());
        userDetails.setAbout(request.getAbout());
        userDetails.setFavourites(request.getFavourites());

        return userDetailsDBORepository.save(userDetails);
    }
}
