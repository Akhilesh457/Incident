package com.incident.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.incident.management.entity.Incident;
import com.incident.management.entity.User;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {
	 Optional<Incident> findFirstByReporterName(String reporterName);
	    boolean existsByIncidentId(String uniqueId);
	    List<Incident> findByUser(User user);
	    Optional<Incident> findByIncidentId(String incidentId);
	    List<Incident> findByUserUserId(Integer userId);
}

