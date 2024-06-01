package com.incident.management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incident.management.dto.IncidentDTO;
import com.incident.management.entity.Incident;
import com.incident.management.entity.Status;
import com.incident.management.entity.User;
import com.incident.management.repository.IncidentRepository;
import com.incident.management.repository.UserRepository;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    public IncidentDTO createIncident(IncidentDTO incidentDTO, Integer userId) {
        User user = getUserByUserId(userId);

        Incident incident = new Incident();
        copyDTOPropertiesToEntity(incidentDTO, incident);
        incident.setUser(user);
        incident.setIncidentId(generateUniqueIncidentId());
        incident.setReportedDateTime(LocalDateTime.now());
        incident.setStatus(incidentDTO.getStatus() != null ? incidentDTO.getStatus() : Status.OPEN);

        Incident createdIncident = incidentRepository.save(incident);
        return convertToDTO(createdIncident);
    }

    public Optional<Incident> getIncidentById(String Incidentid) {
    	System.out.println("Searching for incidentId: " + Incidentid);
        Optional<Incident> incidentOpt = incidentRepository.findByIncidentId(Incidentid);
        System.out.println("Incident found: " + incidentOpt.isPresent());
        return incidentOpt;
    }

    public List<IncidentDTO> getAllIncidentsByUser(String username) {
        User user = getUserByUserName(username);
        List<Incident> incidents = incidentRepository.findByUser(user);
        return incidents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public IncidentDTO updateIncidentDetails(Integer id, IncidentDTO IncidentDTO) {
        Optional<Incident> incidentOptional = incidentRepository.findById(id);
        if (!incidentOptional.isPresent()) {
            return null; // or throw an exception
        }

        Incident incident = incidentOptional.get();

        if (IncidentDTO.getReporterName() != null) {
            incident.setReporterName(IncidentDTO.getReporterName());
        }
        if (IncidentDTO.getIncidentDetails() != null) {
            incident.setIncidentDetails(IncidentDTO.getIncidentDetails());
        }
        if (IncidentDTO.getPriority() != null) {
            incident.setPriority(IncidentDTO.getPriority());
        }
        if (incident.getStatus() != Status.CLOSED) {
            if (IncidentDTO.getStatus() != null && (IncidentDTO.getStatus() == Status.OPEN || IncidentDTO.getStatus() == Status.IN_PROGRESS || IncidentDTO.getStatus() == Status.CLOSED)) {
                incident.setStatus(IncidentDTO.getStatus());
            }
        }


        
        incidentRepository.save(incident);

       
        return IncidentDTO.convertToDTO(incident);
    }

    private IncidentDTO convertToDTO(Incident incident) {
        IncidentDTO incidentDTO = new IncidentDTO();
        BeanUtils.copyProperties(incident, incidentDTO);
        incidentDTO.setUserId(incident.getUser().getUserId());
        return incidentDTO;
    }

    private void copyDTOPropertiesToEntity(IncidentDTO incidentDTO, Incident incident) {
        BeanUtils.copyProperties(incidentDTO, incident, "id", "incidentId", "reportedDateTime", "user");
    }
    //make sure its unique
    private String generateUniqueIncidentId() {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String uniqueId;
        do {
            int randomNumber = (int) (Math.random() * 90000) + 10000;
            uniqueId = "RMG" + randomNumber + year;
        } while (incidentRepository.existsByIncidentId(uniqueId));
        return uniqueId;
    }

    private User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    private User getUserByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }
    public List<Incident> getIncidentsByUserId(Integer userId) {
        return incidentRepository.findByUserUserId(userId);
    }
}
