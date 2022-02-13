package com.drone.service;

import java.util.List;

import javax.validation.Valid;

import com.drone.dto.DroneDto;
import com.drone.dto.MedicationDto;

public interface DroneService {

	DroneDto register(@Valid DroneDto dto);

	DroneDto load(Integer id, @Valid List<MedicationDto> dtos );

	List<MedicationDto> getLoadedMedicationForDrone(Integer id);

	List<DroneDto> getLoadAvailableDrone();

	DroneDto getDroneById(Integer id);

}
