package com.saathchalein_backend.saathchalein_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsRequest {
    private String city;
    private String pincode;
    private String about;
    private String favourites;
}
