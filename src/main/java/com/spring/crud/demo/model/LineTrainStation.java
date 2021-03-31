package com.spring.crud.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LineTrainStation implements Serializable {
	@Id
	@GeneratedValue
	private int lineId;
	private String name;
	@ManyToMany(mappedBy = "lines")
	@JsonIgnore
	private List<TrainStation> trainStations = new ArrayList<>();

	public LineTrainStation() {
		super();
	}

	public LineTrainStation(String name) {
		super();
		this.name = name;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrainStation> getTrainStations() {
		return trainStations;
	}

	public void setTrainStations(List<TrainStation> trainStations) {
		this.trainStations = trainStations;
	}

}
