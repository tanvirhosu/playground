package com.playground.userservice.controller;

import com.playground.userservice.dto.CreateProfileRequest;
import com.playground.userservice.dto.UpdateProfileRequest;
import com.playground.userservice.dto.UserProfileDTO;
import com.playground.userservice.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping
    public ResponseEntity<UserProfileDTO> createProfile(@Valid @RequestBody CreateProfileRequest request) {
        String userId = getCurrentUserId();
        UserProfileDTO profile = userProfileService.createProfile(request, userId);
        return ResponseEntity.created(URI.create("/api/v1/profile")).body(profile);
    }

    @GetMapping
    public ResponseEntity<UserProfileDTO> getProfile() {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(userProfileService.getProfile(userId));
    }

    @PutMapping
    public ResponseEntity<UserProfileDTO> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(userProfileService.updateProfile(request, userId));
    }

    @PatchMapping
    public ResponseEntity<UserProfileDTO> patchProfile(@RequestBody UpdateProfileRequest request) {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(userProfileService.patchProfile(request, userId));
    }
}
