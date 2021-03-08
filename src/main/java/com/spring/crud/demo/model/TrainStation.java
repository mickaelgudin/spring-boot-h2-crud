package com.spring.crud.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class TrainStation implements Serializable {

	@Id
	@GeneratedValue
	private int trainStationId;
	private String name;
	private Double longitude;
	private Double latitude;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "stations_lines", joinColumns = @JoinColumn(name = "trainStationId"), inverseJoinColumns = @JoinColumn(name = "lineId"))
	private List<LineTrainStation> lines = new ArrayList<>();

	public TrainStation() {
		super();
	}

	public TrainStation(String name, Double longitude, Double latitude) {
		super();
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getTrainStationId() {
		return trainStationId;
	}

	public void setTrainStationId(int trainStationId) {
		this.trainStationId = trainStationId;
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

	public List<LineTrainStation> getLines() {
		return lines;
	}

	public void setLines(List<LineTrainStation> lines) {
		this.lines = lines;
	}

}
