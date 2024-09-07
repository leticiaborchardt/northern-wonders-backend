package com.travel.northernwonders.repositories;

import com.travel.northernwonders.domain.travelpackage.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, String> {
}
