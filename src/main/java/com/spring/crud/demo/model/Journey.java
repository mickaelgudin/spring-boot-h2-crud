package com.spring.crud.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.spring.crud.demo.utils.HelperUtil;

@Entity
public class Journey implements Serializable{
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private TrainStation departureStation;

	@ManyToOne
	private TrainStation arrivalStation;

	@ManyToOne
	private LineTrainStation line;

	private LocalDateTime departureDate;

	private LocalDateTime arrivalDate;

	private double farePrice = 0;

	public Journey() {
		super();
	}

	public Journey(TrainStation departureStation, TrainStation arrivalStation, LocalDateTime departureDate,
			LocalDateTime arrivalDate, double farePrice, LineTrainStation line) {
		super();
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
		this.departureDate = HelperUtil.getFormattedDate(departureDate);
		this.arrivalDate = HelperUtil.getFormattedDate(arrivalDate);
		this.farePrice = farePrice;
		this.line = line;
	}

	public TrainStation getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(TrainStation departureStation) {
		this.departureStation = departureStation;
	}

	public TrainStation getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(TrainStation arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public double getFarePrice() {
		return farePrice;
	}

	public void setFarePrice(double farePrice) {
		this.farePrice = farePrice;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LineTrainStation getLine() {
		return line;
	}

	public void setLine(LineTrainStation line) {
		this.line = line;
	}

}
