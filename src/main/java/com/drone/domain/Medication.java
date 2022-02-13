package com.drone.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.drone.dto.MedicationDto;

@Entity
@Table(name = "medication")
public class Medication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 788815181977419455L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "drone_id", nullable = false)
	private Drone drone;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "weight", nullable = false)
	private Double weight;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "image", nullable = false, unique = true)
	private String image;

	@Column(name = "active", nullable = false)
	private boolean active;

	public Medication() {
	}

	public Medication(Drone dron, MedicationDto dto) {
		this.drone = new Drone();
		this.drone.setId(dron.getId());
		this.drone.setBatteryCapacity(dron.getBatteryCapacity());
		this.drone.setModel(dron.getModel());
		this.drone.setSerialNumber(dron.getSerialNumber());
		this.drone.setState(dron.getState());
		this.drone.setWeightLimit(dron.getWeightLimit());
		this.name = dto.getName();
		this.weight = dto.getWeight();
		this.code = dto.getCode();
		this.active = true;
		this.image = dto.getImage();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Drone getDone() {
		return drone;
	}

	public void setDone(Drone drone) {
		this.drone = drone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((drone == null) ? 0 : drone.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Medication other = (Medication) obj;
		if (active != other.active)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (drone == null) {
			if (other.drone != null)
				return false;
		} else if (!drone.equals(other.drone))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	public MedicationDto buidDto() {
		MedicationDto dto = new MedicationDto();
		dto.setCode(code);
		dto.setImage(image);
		dto.setName(name);
		dto.setWeight(weight);
		return dto;
	}

}
