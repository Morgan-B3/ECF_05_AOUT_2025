package org.example.environement.controller;

import org.example.environement.dto.observation.ObservationDtoReceive;
import org.example.environement.dto.observation.ObservationDtoResponse;
import org.example.environement.dto.specie.SpecieDtoReceive;
import org.example.environement.dto.specie.SpecieDtoResponse;
import org.example.environement.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private ObservationService observationService;

    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @GetMapping
    public ResponseEntity<List<ObservationDtoResponse>> getObservations (@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int pageNumber){
        return ResponseEntity.ok(observationService.get(pageSize, pageNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservationDtoResponse> getObservationById (@PathVariable long id){
        return ResponseEntity.ok(observationService.get(id));
    }

    @PostMapping
    public ResponseEntity<ObservationDtoResponse> createObservation (@RequestBody ObservationDtoReceive observationDtoReceive){
        return ResponseEntity.status(HttpStatus.CREATED).body(observationService.create(observationDtoReceive));
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<ObservationDtoResponse>> getObservationsByLocation (@RequestParam String location){
        return ResponseEntity.ok(observationService.getByLocation(location));
    }

    @GetMapping("/by-species/{speciesId}")
    public ResponseEntity<List<ObservationDtoResponse>> getObservationsBySpecies (@PathVariable Long speciesId){
        return ResponseEntity.ok(observationService.getBySpecie(speciesId));
    }
}
