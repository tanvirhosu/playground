package com.playground.userservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserProfileDTO {
    private String userId;
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
