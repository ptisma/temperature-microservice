package com.rasus.lab.temperaturemicroservice.repo;

import com.rasus.lab.temperaturemicroservice.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}