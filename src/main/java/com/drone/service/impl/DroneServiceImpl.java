package com.drone.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drone.domain.Drone;
import com.drone.domain.Medication;
import com.drone.dto.DroneDto;
import com.drone.dto.MedicationDto;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import com.drone.service.DroneService;
import com.drone.utill.DroneLoader;
import com.drone.utill.DroneState;
import com.droneexception.DroneLoadingException;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	DroneRepository droneRepository;

	@Autowired
	MedicationRepository medicationRepository;

	@Override
	@Transactional
	public DroneDto register(@Valid DroneDto dto) {

		Drone drone = droneRepository.save(new Drone(dto));
		return drone.buidDto();
	}

	@Override
	@Transactional
	public DroneDto load(Integer id, @Valid List<MedicationDto> dtos) {
		Drone drone = droneRepository.getById(id);
		if (drone.getState().equals(DroneState.LOADING) || drone.getState().equals(DroneState.DELIVERING)) {
			throw new DroneLoadingException("Drone can not load,Drone is " + drone.getState() + " state");
		}
		Double sum = dtos.stream().mapToDouble(o -> o.getWeight()).sum();
		List<Medication> medications = medicationRepository.findByDrone(drone);
		Double loadsum = 0.0;
		if (medications != null && !medications.isEmpty()) {
			loadsum = medications.stream().mapToDouble(o -> o.getWeight()).sum();
		}
		if ((drone.getWeightLimit() - loadsum) < sum) {
			throw new DroneLoadingException("Drone is only able to carry weight " + drone.getWeightLimit());
		}

		if (drone.getBatteryCapacity() <= 25) {
			throw new DroneLoadingException("Drone battery level is **below 25%**");
		}
		drone.setState(DroneState.LOADING);
		droneRepository.save(drone);
		DroneLoader droneLoader = new DroneLoader(medicationRepository, drone, dtos, droneRepository);
		droneLoader.start();
		return drone.buidDto();
	}

	@Override
	public List<MedicationDto> getLoadedMedicationForDrone(Integer id) {
		Drone drone = new Drone();
		drone.setId(id);
		List<Medication> medications = medicationRepository.findByDrone(drone);
		List<Medication> result = medications.stream().filter(e -> e.isActive()).collect(Collectors.toList());
		List<MedicationDto> resultDto = result.stream().map(e -> e.buidDto()).collect(Collectors.toList());
		return resultDto;
	}

	@Override
	public List<DroneDto> getLoadAvailableDrone() {
		List<Drone> drones = droneRepository.findAll();
		List<DroneDto> resultDto = drones.stream().filter(e -> {
			List<Medication> medications = medicationRepository.findByDrone(e);
			Double sum = medications.stream().mapToDouble(o -> o.getWeight()).sum();
			if (e.getWeightLimit() > sum
					&& (!e.getState().equals(DroneState.LOADING) || !e.getState().equals(DroneState.DELIVERING))) {
				return true;
			} else {
				return false;
			}
		}).map(e -> e.buidDto()).collect(Collectors.toList());
		return resultDto;
	}

	@Override
	public DroneDto getDroneById(Integer id) {
		Drone drone = droneRepository.getById(id);
		return drone.buidDto();
	}

}
