package com.playground.userservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateProfileRequest {
    @Email(message = "Invalid email format")
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String street;
    private String city;
    private String country;
    private String postalCode;
}
