package com.drone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.dto.DroneDto;
import com.drone.dto.MedicationDto;
import com.drone.service.DroneService;

@RestController
@RequestMapping("/drone")
public class DroneController {

	@Autowired
	DroneService droneService;

	@PostMapping(path = "/register")
	public ResponseEntity<DroneDto> register(@RequestBody @Valid DroneDto dto) {
		ResponseEntity<DroneDto> entity = new ResponseEntity<DroneDto>(droneService.register(dto), HttpStatus.OK);
		return entity;
	}

	@PostMapping(path = "/{id}/load")
	public ResponseEntity<DroneDto> load(@RequestBody @Valid List<MedicationDto> dtos, @PathVariable Integer id) {
		ResponseEntity<DroneDto> entity = new ResponseEntity<DroneDto>(droneService.load(id, dtos), HttpStatus.OK);
		return entity;
	}

	@GetMapping(path = "/{id}/medication/loaded")
	public ResponseEntity<List<MedicationDto>> getLoadedMedicationForDrone(@PathVariable Integer id) {
		ResponseEntity<List<MedicationDto>> entity = new ResponseEntity<List<MedicationDto>>(
				droneService.getLoadedMedicationForDrone(id), HttpStatus.OK);
		return entity;
	}

	@GetMapping(path = "/load/available")
	public ResponseEntity<List<DroneDto>> getLoadAvailableDrone() {
		ResponseEntity<List<DroneDto>> entity = new ResponseEntity<List<DroneDto>>(droneService.getLoadAvailableDrone(),
				HttpStatus.OK);
		return entity;
	}

	@GetMapping(path = "/{id}/batterylevel")
	public ResponseEntity<Double> getDroneBatteryLevel(@PathVariable Integer id) {
		ResponseEntity<Double> entity = new ResponseEntity<Double>(droneService.getDroneById(id).getBatteryCapacity(),
				HttpStatus.OK);
		return entity;
	}

}
