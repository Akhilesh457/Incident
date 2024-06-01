package com.incident.management.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PincodeService {

    private static final String PINCODE_API_URL = "https://api.postalpincode.in/pincode/";

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, String> getPincodeDetails(String pincode) {
        String pincodeUrl = PINCODE_API_URL + pincode;

        try {
            // Make HTTP GET request
            String pincodeDetails = restTemplate.getForObject(pincodeUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(pincodeDetails);

            // Parse JSON response
            JsonNode postOffice = root.path(0).path("PostOffice").get(0);
            String state = postOffice.path("State").asText();
            String country = postOffice.path("Country").asText();
            
            // Create a map with state and country
            return Map.of("State", state, "Country", country);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
            return null; // or throw custom exception
        }
    }
}