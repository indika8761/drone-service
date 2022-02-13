package com.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drone.domain.Drone;
import com.drone.utill.DroneState;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {

	List<Drone> findByState(DroneState state);

	Drone findBySerialNumber(String serialNumber);

}
