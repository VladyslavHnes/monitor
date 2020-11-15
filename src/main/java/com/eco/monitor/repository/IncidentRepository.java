package com.eco.monitor.repository;

import com.eco.monitor.entity.Incident;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {

    List<Incident> findByUserId(Integer userId);
}
