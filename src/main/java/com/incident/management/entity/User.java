package com.incident.management.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.incident.management.dto.IncidentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId ;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber; 

    @NonNull
    private String address;

    @NonNull
    private String pinCode;

    @NonNull
    private String city;

    @NonNull
    private String country;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
    

    @NonNull
    @Enumerated(EnumType.STRING)
    private IncidentType incidentType;
    
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Incident> incidents;
 // Helper method to add incidents
    public void addIncident(Incident incident) {
        incident.setUser(this);
        this.incidents.add(incident);
    }

    // Getters and setters
    public void setIncidents(List<Incident> list) {
        List<Incident> incidents = new ArrayList<>();
        for (Incident incidentDTO : list) {
            Incident incident = new Incident();
            incident.setIncidentId(incidentDTO.getIncidentId());
            incident.setIncidentDetails(incidentDTO.getIncidentDetails());
            incident.setReportedDateTime(incidentDTO.getReportedDateTime());
            incident.setPriority(incidentDTO.getPriority());
            incident.setStatus(incidentDTO.getStatus());
            incidents.add(incident);
        }
        this.incidents = incidents;
    }
}
//add unique annotation