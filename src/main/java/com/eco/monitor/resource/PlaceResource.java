package com.eco.monitor.resource;

import com.eco.monitor.dto.IncidentDto;
import com.eco.monitor.dto.PlaceDto;
import com.eco.monitor.entity.Incident;
import com.eco.monitor.request.UpdatePlaceRequest;
import com.eco.monitor.service.IncidentService;
import com.eco.monitor.service.TokenService;
import com.eco.monitor.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/places")
public class PlaceResource {

    @Autowired
    public TokenService tokenService;

    @Autowired
    public IncidentService incidentService;

    @PostMapping
    public ResponseEntity createPlace(
            @RequestBody UpdatePlaceRequest request,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        String token = TokenUtil.resolveToken(httpServletRequest).orElseThrow(Exception::new);
        Integer userId = tokenService.decodeJWT(token);

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
        incidentDto.setUserId(userId);
        incidentService.createIncident(incidentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IncidentDto>> getIncidents() {
        return ResponseEntity.ok().body(incidentService.getIncidents());
    }

    @GetMapping(path = "/my")
    public ResponseEntity<List<IncidentDto>> getMyIncidents(@RequestParam Integer userId) {
        return ResponseEntity.ok().body(incidentService.getMyIncidents(userId));
    }

    @GetMapping(path = "/{incidentId}")
    public ResponseEntity<IncidentDto> getIncident(@PathVariable Integer incidentId) throws Exception {
        return ResponseEntity.ok().body(incidentService.getIncident(incidentId));
    }
}
