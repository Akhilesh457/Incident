package com.incident.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incident.management.dto.IncidentDTO;
import com.incident.management.entity.Incident;
import com.incident.management.service.IncidentService;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;
    
    @PostMapping("/{userId}")
    public ResponseEntity<IncidentDTO> createIncident(@RequestBody IncidentDTO incidentDTO,@PathVariable Integer userId) {
        IncidentDTO createdIncident = incidentService.createIncident(incidentDTO,userId);
        return ResponseEntity.ok(createdIncident);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentDTO> updateIncidentDetails(@PathVariable Integer id, @RequestBody IncidentDTO IncidentDTO) {
        IncidentDTO updatedIncident = incidentService.updateIncidentDetails(id, IncidentDTO);
        if (updatedIncident == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedIncident);
    }



	// get incident by ID
    @GetMapping("/id")
    public ResponseEntity<Incident> getIncidentById(@RequestParam String incidentId) {
        Optional<Incident> incident = incidentService.getIncidentById(incidentId);
        return incident.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/user/{userId}")
    public List<Incident> getIncidentsByUserId(@PathVariable Integer userId) {
        return incidentService.getIncidentsByUserId(userId);
    }

}
