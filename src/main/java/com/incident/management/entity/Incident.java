package com.incident.management.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String incidentId;

    @NonNull
    private String reporterName;

    @NonNull
    private String incidentDetails;

    @NonNull
    private LocalDateTime reportedDateTime;

    
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NonNull
    @Enumerated
    private IncidentType incidentType;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "userId", nullable = false)
    @JsonBackReference
    private User user;

    
    // Getters and setters
}

