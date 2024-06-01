package com.incident.management.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pincode {

    @JsonProperty("PostOffice")
    private List<PostOffice> postOffices;

    // Getters and setters

    @Setter
    @Getter
    public static class PostOffice {

        @JsonProperty("State")
        private String state;

        @JsonProperty("Country")
        private String country;

        // Getters and setters

    }
}