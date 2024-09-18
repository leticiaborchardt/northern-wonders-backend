package com.travel.northernwonders.services;

import com.travel.northernwonders.domain.destination.Destination;
import com.travel.northernwonders.domain.destination.DestinationDTO;
import com.travel.northernwonders.domain.travelpackage.TravelPackage;
import com.travel.northernwonders.domain.travelpackage.TravelPackageDTO;
import com.travel.northernwonders.repositories.TravelPackageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;
    private final DestinationService destinationService;

    public TravelPackageService(TravelPackageRepository travelPackageRepository, DestinationService destinationService) {
        this.travelPackageRepository = travelPackageRepository;
        this.destinationService = destinationService;
    }

    public List<TravelPackage> findAll() {
        return travelPackageRepository.findAll();
    }

    public TravelPackage findById(String id) {
        return travelPackageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Travel package not found with ID: " + id));
    }

    @Transactional
    public TravelPackage createTravelPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackage = new TravelPackage();
        BeanUtils.copyProperties(travelPackageDTO, travelPackage);

        travelPackage.setDestination(findOrCreateDestination(travelPackageDTO.destination()));

        return travelPackageRepository.save(travelPackage);
    }

    @Transactional
    public TravelPackage updateTravelPackage(String id, TravelPackageDTO travelPackageDTO) {
        TravelPackage existingTravelPackage = this.findById(id);
        BeanUtils.copyProperties(travelPackageDTO, existingTravelPackage);

        existingTravelPackage.setDestination(findOrCreateDestination(travelPackageDTO.destination()));

        return travelPackageRepository.save(existingTravelPackage);
    }

    @Transactional
    public void deleteTravelPackage(String id) {
        this.findById(id);
        travelPackageRepository.deleteById(id);
    }

    @Transactional
    protected Destination findOrCreateDestination(Destination destination) {
        return destinationService.getDestinationById(destination.getId())
                .orElseGet(() -> destinationService.createDestination(
                        new DestinationDTO(destination.getRegion(), destination.getCity())
                ));
    }
}
