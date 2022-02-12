package com.drone.utill;

import com.drone.domain.Drone;
import com.drone.domain.Medication;
import com.drone.dto.MedicationDto;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;

public class DroneLoader extends Thread {

	private MedicationRepository medicationRepository;
	private DroneRepository droneRepository;
	private Drone dron;
	private MedicationDto medicationDto;

	public DroneLoader(MedicationRepository medicationRepository, Drone dron, MedicationDto medicationDto,
			DroneRepository droneRepository) {
		super();
		this.medicationRepository = medicationRepository;
		this.dron = dron;
		this.medicationDto = medicationDto;
		this.droneRepository = droneRepository;
	}

	@Override
	public void run() {
		Medication medication = new Medication(dron, medicationDto);

		try {

			int waitCount = (int) (medicationDto.getWeight() / 5);
			for (int i = 0; i < waitCount; i++) {

				medication.setWeight((double) (5 * (i + 1)));
				medicationRepository.save(medication);
				dron.setBatteryCapacity((dron.getBatteryCapacity() - (dron.getBatteryCapacity() * 0.001) / 100));
				droneRepository.save(dron);
				Thread.sleep(2000);

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		medication.setWeight(medicationDto.getWeight());
		droneRepository.save(dron);
		dron.setState(DroneState.LOADED);
		droneRepository.save(dron);
		super.run();
	}

}
