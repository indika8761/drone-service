package com.drone.utill;

import java.util.List;

import com.drone.domain.Drone;
import com.drone.domain.Medication;
import com.drone.dto.MedicationDto;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;

public class DroneLoader extends Thread {

	private MedicationRepository medicationRepository;
	private DroneRepository droneRepository;
	private Drone dron;
	private List<MedicationDto> medicationDtos;

	public DroneLoader(MedicationRepository medicationRepository, Drone dron, List<MedicationDto> medicationDtos,
			DroneRepository droneRepository) {
		super();
		this.medicationRepository = medicationRepository;
		this.dron = dron;
		this.medicationDtos = medicationDtos;
		this.droneRepository = droneRepository;
	}

	@Override
	public void run() {
		try {
			
			for(MedicationDto dto :medicationDtos) {
				Medication medication = new Medication(dron, dto);
				int waitCount = (int) (dto.getWeight() / 5);
				for (int i = 0; i < waitCount; i++) {

					medication.setWeight((double) (5 * (i + 1)));
					medicationRepository.save(medication);
					dron.setBatteryCapacity((dron.getBatteryCapacity() - (dron.getBatteryCapacity() * 0.001) / 100));
					droneRepository.save(dron);
					Thread.sleep(2000);
				}
				
				medication.setWeight(dto.getWeight());
				droneRepository.save(dron);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		dron.setState(DroneState.LOADED);
		droneRepository.save(dron);
		super.run();
	}

}
