package com.saathchalein_backend.saathchalein_backend.controller;

import com.saathchalein_backend.saathchalein_backend.dbo.UserDBO;
import com.saathchalein_backend.saathchalein_backend.dbo.UserDetailsDBO;
import com.saathchalein_backend.saathchalein_backend.dto.UserDetailsRequest;
import com.saathchalein_backend.saathchalein_backend.dto.UserResponse;
import com.saathchalein_backend.saathchalein_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDBO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}/details")
    public ResponseEntity<UserDetailsDBO> updateUserDetails(@PathVariable Long id,
                                                           @Valid @RequestBody UserDetailsRequest request) {
        return ResponseEntity.ok(userService.updateUserDetails(id, request));
    }
}
