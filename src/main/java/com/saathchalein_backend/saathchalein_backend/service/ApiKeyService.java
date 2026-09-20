package com.saathchalein_backend.saathchalein_backend.service;

import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    private static final String VALID_API_KEY = "saathchalein-api-key";

    public boolean isValidApiKey(String apiKey) {
        return VALID_API_KEY.equals(apiKey);
    }
}
