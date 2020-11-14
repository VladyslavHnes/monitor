package com.eco.monitor.converter;

import com.eco.monitor.dto.IncidentDto;
import com.eco.monitor.dto.PlaceDto;
import com.eco.monitor.entity.Incident;
import com.eco.monitor.entity.Place;

import java.util.List;
import java.util.stream.Collectors;

public class IncidentConverter {

    public static IncidentDto toIncidentDto(Incident incident) {
        IncidentDto incidentDto = new IncidentDto();
        incidentDto.setId(incident.getId());
        incidentDto.setDescription(incident.getDescription());
        incidentDto.setCategory(incident.getCategory());
        incidentDto.setTitle(incident.getTitle());
        incidentDto.setPlace(toPlaceDto(incident.getPlace()));
        incidentDto.setImageUrl(incident.getImage());
        return incidentDto;
    }

    public static PlaceDto toPlaceDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setAddress(place.getAddress());
        placeDto.setLatitude(place.getLatitude());
        placeDto.setLongitude(place.getLongitude());
        return placeDto;
    }

    public static List<IncidentDto> toIncidentDtoList(List<Incident> incidents) {
        return incidents.stream().map(IncidentConverter::toIncidentDto).collect(Collectors.toList());
    }
}
