package com.travel.northernwonders.services;

import com.travel.northernwonders.domain.destination.Destination;
import com.travel.northernwonders.domain.destination.DestinationDTO;
import com.travel.northernwonders.repositories.DestinationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getDestinationById(String id) {
        return destinationRepository.findById(id);
    }

    @Transactional
    public Destination createDestination(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        BeanUtils.copyProperties(destinationDTO, destination);

        return destinationRepository.save(destination);
    }
}
