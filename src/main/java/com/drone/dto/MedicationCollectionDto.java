package com.drone.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class MedicationCollectionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Valid
	List<MedicationDto> medicationItems;

	public List<MedicationDto> getMedicationItems() {
		return medicationItems;
	}

	public void setMedicationItems(List<MedicationDto> medicationItems) {
		this.medicationItems = medicationItems;
	}

}
