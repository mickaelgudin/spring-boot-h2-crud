package com.spring.crud.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table
public class Journey {
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "departure_station_id")
	private TrainStation departureStation;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "arrival_station_id")
	private LineTrainStation arrivalStation;

	private LocalDateTime date;

	private double farePrice = 0;

	public Journey() {
		super();
	}

	public Journey(TrainStation departureStation, LineTrainStation arrivalStation, LocalDateTime date,
			double farePrice) {
		super();
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
		this.date = date;
		this.farePrice = farePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TrainStation getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(TrainStation departureStation) {
		this.departureStation = departureStation;
	}

	public LineTrainStation getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(LineTrainStation arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public double getFarePrice() {
		return farePrice;
	}

	public void setFarePrice(double farePrice) {
		this.farePrice = farePrice;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
