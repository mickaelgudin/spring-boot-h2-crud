package com.spring.crud.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class TrainStation implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Double longitude;
	private Double latitude;
	
	

	public TrainStation() {
		super();
	}

	public TrainStation( String name, Double longitude, Double latitude) {
		super();
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
