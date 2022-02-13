package com.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drone.domain.Drone;
import com.drone.domain.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

	List<Medication> findByDrone(Drone id);

	Medication findByCode(String code);

}
