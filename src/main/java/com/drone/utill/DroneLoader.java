package com.drone.utill;

import java.util.List;

import com.drone.domain.Drone;
import com.drone.domain.Medication;
import com.drone.dto.MedicationDto;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import com.droneexception.DroneLoadingException;

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

			for (MedicationDto dto : medicationDtos) {

				Medication medication = medicationRepository.findByCode(dto.getCode());
				if (medication == null) {
					medication = new Medication(dron, dto);
					medication.setWeight(0.00);
				}
				int waitCount = (int) (dto.getWeight() / 5);
				for (int i = 0; i < waitCount; i++) {

					medication.setWeight(medication.getWeight() + 5);
					medicationRepository.save(medication);
					dron.setBatteryCapacity((dron.getBatteryCapacity() - (dron.getBatteryCapacity() * 0.01) / 100));
					droneRepository.save(dron);
					if (dron.getBatteryCapacity() <= 0) {
						dron.setState(DroneState.RETURNING);
						droneRepository.save(dron);
						throw new DroneLoadingException(Messages.getString("DroneLoader.0")); //$NON-NLS-1$
					}
					Thread.sleep(2000);
				}

			}

		} catch (InterruptedException e) {
			dron.setState(DroneState.RETURNING);
			droneRepository.save(dron);
			throw new DroneLoadingException(e);
		}

		dron.setState(DroneState.LOADED);
		droneRepository.save(dron);
		super.run();
	}

}
