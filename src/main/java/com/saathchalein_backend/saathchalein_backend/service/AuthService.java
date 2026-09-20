package com.saathchalein_backend.saathchalein_backend.service;

import com.saathchalein_backend.saathchalein_backend.dbo.RoleDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDBORepository;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDetailsDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDetailsDBORepository;
import com.saathchalein_backend.saathchalein_backend.dto.AdminCreateRequest;
import com.saathchalein_backend.saathchalein_backend.dto.AuthResponse;
import com.saathchalein_backend.saathchalein_backend.dto.LoginRequest;
import com.saathchalein_backend.saathchalein_backend.dto.SignupRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDBORepository userDBORepository;
    private final UserDetailsDBORepository userDetailsDBORepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserDBORepository userDBORepository, UserDetailsDBORepository userDetailsDBORepository, JwtService jwtService) {
        this.userDBORepository = userDBORepository;
        this.userDetailsDBORepository = userDetailsDBORepository;
        this.jwtService = jwtService;
    }

    public UserDBO signup(SignupRequest request) {
        if (userDBORepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userDBORepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserDBO user = new UserDBO();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserDBO savedUser = userDBORepository.save(user);

        UserDetailsDBO details = new UserDetailsDBO();
        details.setUser(savedUser);
        details.setRole(RoleDBO.USER);
        userDetailsDBORepository.save(details);

        return savedUser;
    }

    public UserDBO createAdmin(AdminCreateRequest request) {
        if (request == null || request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username, email and password are required");
        }

        String normalizedEmail = request.getEmail().trim();
        String normalizedUsername = request.getUsername().trim();

        if (normalizedEmail.isEmpty() || normalizedUsername.isEmpty() || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Username, email and password are required");
        }

        if (userDBORepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userDBORepository.existsByUsername(normalizedUsername)) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserDBO user = new UserDBO();
        user.setUsername(normalizedUsername);
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserDBO savedUser = userDBORepository.save(user);

        RoleDBO requestedRole = RoleDBO.ADMIN;
        if (request.getRole() != null && !request.getRole().trim().isEmpty()) {
            try {
                requestedRole = RoleDBO.valueOf(request.getRole().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Role must be ADMIN or USER");
            }
        }

        UserDetailsDBO details = new UserDetailsDBO();
        details.setUser(savedUser);
        details.setRole(requestedRole);
        userDetailsDBORepository.save(details);

        return savedUser;
    }

    public AuthResponse login(LoginRequest request) {
        UserDBO user = userDBORepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getUsername(), user.getEmail());
    }
}
