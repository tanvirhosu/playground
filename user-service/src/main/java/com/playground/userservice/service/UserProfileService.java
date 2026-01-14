package com.playground.userservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playground.userservice.dto.CreateProfileRequest;
import com.playground.userservice.dto.UpdateProfileRequest;
import com.playground.userservice.dto.UserProfileDTO;
import com.playground.userservice.exception.ProfileAlreadyExistsException;
import com.playground.userservice.exception.ProfileNotFoundException;
import com.playground.userservice.model.UserProfile;
import com.playground.userservice.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Transactional
    public UserProfileDTO createProfile(CreateProfileRequest request, String userId) {
        if (userProfileRepository.existsByUserId(userId)) {
            throw new ProfileAlreadyExistsException("Profile already exists for user: " + userId);
        }
        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new ProfileAlreadyExistsException("Email already in use: " + request.getEmail());
        }

        UserProfile profile = new UserProfile();
        profile.setUserId(userId);
        profile.setEmail(request.getEmail());
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setBirthDate(request.getBirthDate());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setStreet(request.getStreet());
        profile.setCity(request.getCity());
        profile.setCountry(request.getCountry());
        profile.setPostalCode(request.getPostalCode());

        UserProfile saved = userProfileRepository.save(profile);
        return mapToDTO(saved);
    }

    public UserProfileDTO getProfile(String userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for user: " + userId));
        return mapToDTO(profile);
    }

    @Transactional
    public UserProfileDTO updateProfile(UpdateProfileRequest request, String userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for user: " + userId));
        
        updateProfileFields(profile, request);
        return mapToDTO(userProfileRepository.save(profile));
    }
    
    @Transactional
    public UserProfileDTO patchProfile(UpdateProfileRequest request, String userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for user: " + userId));
        
        updateProfileFields(profile, request);
        return mapToDTO(userProfileRepository.save(profile));
    }

    private void updateProfileFields(UserProfile profile, UpdateProfileRequest request) {
        if (request.getEmail() != null && !request.getEmail().equals(profile.getEmail())) {
             if (userProfileRepository.existsByEmail(request.getEmail())) {
                throw new ProfileAlreadyExistsException("Email already in use");
             }
             profile.setEmail(request.getEmail());
        }
        if (request.getFirstName() != null) profile.setFirstName(request.getFirstName());
        if (request.getLastName() != null) profile.setLastName(request.getLastName());
        if (request.getBirthDate() != null) profile.setBirthDate(request.getBirthDate());
        if (request.getPhoneNumber() != null) profile.setPhoneNumber(request.getPhoneNumber());
        if (request.getStreet() != null) profile.setStreet(request.getStreet());
        if (request.getCity() != null) profile.setCity(request.getCity());
        if (request.getCountry() != null) profile.setCountry(request.getCountry());
        if (request.getPostalCode() != null) profile.setPostalCode(request.getPostalCode());
    }

    private UserProfileDTO mapToDTO(UserProfile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setUserId(profile.getUserId());
        dto.setEmail(profile.getEmail());
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setBirthDate(profile.getBirthDate());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setStreet(profile.getStreet());
        dto.setCity(profile.getCity());
        dto.setCountry(profile.getCountry());
        dto.setPostalCode(profile.getPostalCode());
        return dto;
    }
}
