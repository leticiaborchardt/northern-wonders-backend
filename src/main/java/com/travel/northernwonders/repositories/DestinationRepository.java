package com.travel.northernwonders.repositories;

import com.travel.northernwonders.domain.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {
}
