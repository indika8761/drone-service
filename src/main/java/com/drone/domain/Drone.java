package com.drone.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.drone.dto.DroneDto;
import com.drone.utill.DroneModel;
import com.drone.utill.DroneState;

@Entity
@Table(name = "drone")
public class Drone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "serial_number", nullable = false, unique = true)
	private String serialNumber;

	@Column(name = "model", nullable = false)
	private DroneModel model;

	@Column(name = "weight_limit", nullable = false)
	private Double weightLimit;

	@Column(name = "battery_capacity", nullable = false)
	private Double batteryCapacity;

	@Column(name = "state", nullable = false)
	private DroneState state;

	public Drone() {
	}

	public Drone(DroneDto dto) {
		this.serialNumber = dto.getSerialNumber();
		this.model = dto.getModel();
		this.batteryCapacity = dto.getBatteryCapacity();
		this.state = dto.getState();
		if (dto.getState() == null) {
			this.state = DroneState.IDLE;
		}
		this.weightLimit = dto.getWeightLimit();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public DroneModel getModel() {
		return model;
	}

	public void setModel(DroneModel model) {
		this.model = model;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Double weightLimit) {
		this.weightLimit = weightLimit;
	}

	public Double getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(Double batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public DroneState getState() {
		return state;
	}

	public void setState(DroneState state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batteryCapacity == null) ? 0 : batteryCapacity.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((weightLimit == null) ? 0 : weightLimit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drone other = (Drone) obj;
		if (batteryCapacity == null) {
			if (other.batteryCapacity != null)
				return false;
		} else if (!batteryCapacity.equals(other.batteryCapacity))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model != other.model)
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (state != other.state)
			return false;
		if (weightLimit == null) {
			if (other.weightLimit != null)
				return false;
		} else if (!weightLimit.equals(other.weightLimit))
			return false;
		return true;
	}

	public DroneDto buidDto() {
		DroneDto dto = new DroneDto();
		dto.setBatteryCapacity(batteryCapacity);
		dto.setModel(model);
		dto.setSerialNumber(serialNumber);
		dto.setState(state);
		dto.setWeightLimit(weightLimit);
		return dto;
	}

}
