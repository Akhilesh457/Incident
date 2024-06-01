package com.incident.management.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.incident.management.entity.Incident;
import com.incident.management.entity.IncidentType;
import com.incident.management.entity.Priority;
import com.incident.management.entity.Status;
import com.incident.management.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentDTO {
    private Integer id;
    private String incidentId;
    private String reporterName;
    private String incidentDetails;
    private LocalDateTime reportedDateTime;
    private Priority priority;
    private Status status;
    private IncidentType incidentType;
    private Integer userId;

    @JsonBackReference
    private UserDTO user;

    public static IncidentDTO convertToDTO(Incident incident) {
        IncidentDTO incidentDTO = new IncidentDTO();
        incidentDTO.setId(incident.getId());
        incidentDTO.setIncidentId(incident.getIncidentId());
        incidentDTO.setReporterName(incident.getReporterName());
        incidentDTO.setIncidentDetails(incident.getIncidentDetails());
        incidentDTO.setReportedDateTime(incident.getReportedDateTime());
        incidentDTO.setPriority(incident.getPriority());
        incidentDTO.setStatus(incident.getStatus());
        incidentDTO.setIncidentType(incident.getIncidentType());
        incidentDTO.setUserId(incident.getUser().getUserId());
        incidentDTO.setUser(UserDTO.convertToDTO(incident.getUser()));
        return incidentDTO;
    }

    public static Incident convertToEntity(IncidentDTO incidentDTO) {
        Incident incident = new Incident();
        incident.setId(incidentDTO.getId());
        incident.setIncidentId(incidentDTO.getIncidentId());
        incident.setReporterName(incidentDTO.getReporterName());
        incident.setIncidentDetails(incidentDTO.getIncidentDetails());
        incident.setReportedDateTime(incidentDTO.getReportedDateTime());
        incident.setPriority(incidentDTO.getPriority());
        incident.setStatus(incidentDTO.getStatus());
        incident.setIncidentType(incidentDTO.getIncidentType());
        return incident;
    }
}
