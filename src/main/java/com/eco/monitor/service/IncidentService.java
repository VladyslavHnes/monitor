package com.eco.monitor.service;

import com.eco.monitor.dto.IncidentDto;
import com.eco.monitor.entity.Incident;
import com.eco.monitor.entity.Place;
import com.eco.monitor.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eco.monitor.converter.IncidentConverter.toIncidentDto;
import static com.eco.monitor.converter.IncidentConverter.toIncidentDtoList;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public void createIncident(IncidentDto incidentDto) {
        Place place = new Place();
        place.setAddress(incidentDto.getPlace().getAddress());
        place.setLatitude(incidentDto.getPlace().getLatitude());
        place.setLongitude(incidentDto.getPlace().getLongitude());

        Incident incident = new Incident();
        incident.setPlace(place);
        incident.setCategory(incidentDto.getCategory());
        incident.setDescription(incidentDto.getDescription());
        incident.setTitle(incidentDto.getTitle());
        incident.setImage(incidentDto.getImageUrl());

        incidentRepository.save(incident);
    }

    public IncidentDto getIncident(Long incidentId) throws Exception {
        return toIncidentDto(incidentRepository.findById(incidentId).orElseThrow(Exception::new));
    }

    public List<IncidentDto> getIncidents() {
        return toIncidentDtoList((List<Incident>) incidentRepository.findAll());
    }
}
