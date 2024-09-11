package com.travel.northernwonders.controllers;

import com.travel.northernwonders.domain.travelpackage.TravelPackage;
import com.travel.northernwonders.domain.travelpackage.TravelPackageDTO;
import com.travel.northernwonders.services.TravelPackageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("travelpackage")
public class TravelPackageController {
    private final TravelPackageService travelPackageService;

    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @GetMapping
    public ResponseEntity<List<TravelPackage>> getAllTravelPackages() {
        return ResponseEntity.ok(travelPackageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTravelPackageById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(travelPackageService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while retrieving TravelPackage: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(travelPackageService.createTravelPackage(travelPackageDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while creating Travel Package: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTravelPackage(@PathVariable String id, @RequestBody TravelPackageDTO travelPackageDTO) {
        try {
            return ResponseEntity.ok(travelPackageService.updateTravelPackage(id, travelPackageDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while update Travel Package " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTravelPackage(@PathVariable String id) {
        try {
            travelPackageService.deleteTravelPackage(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while deleting Travel Package " + e.getMessage());
        }
    }
}
