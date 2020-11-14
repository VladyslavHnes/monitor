package com.eco.monitor.repository;

import com.eco.monitor.entity.Incident;
import org.springframework.data.repository.CrudRepository;

public interface IncidentRepository extends CrudRepository<Incident, Long> {
}
