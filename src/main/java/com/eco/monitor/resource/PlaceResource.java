package com.eco.monitor.resource;

import com.eco.monitor.dto.IncidentDto;
import com.eco.monitor.dto.PlaceDto;
import com.eco.monitor.request.UpdatePlaceRequest;
import com.eco.monitor.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/places")
public class PlaceResource {

    @Autowired
    public IncidentService incidentService;

    @PostMapping
    public ResponseEntity updatePlace(
            @RequestBody UpdatePlaceRequest request
    ) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setAddress(request.getAddress());
        placeDto.setLongitude(Double.parseDouble(request.getLongitude()));
        placeDto.setLatitude(Double.parseDouble(request.getLatitude()));

        IncidentDto incidentDto = new IncidentDto();
        incidentDto.setTitle(request.getTitle());
        incidentDto.setCategory(request.getCategory());
        incidentDto.setDescription(request.getDescription());
        incidentDto.setPlace(placeDto);
        incidentDto.setImageUrl(request.getImageUrl());
        incidentService.createIncident(incidentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IncidentDto>> getIncidents() {
        return ResponseEntity.ok().body(incidentService.getIncidents());
    }

    @GetMapping(path = "/{incidentId}")
    public ResponseEntity<IncidentDto> getIncident(@PathVariable Long incidentId) throws Exception {
        return ResponseEntity.ok().body(incidentService.getIncident(incidentId));
    }
}
