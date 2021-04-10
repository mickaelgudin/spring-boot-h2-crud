package com.spring.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.spring.crud.demo.service.impl.JourneyServiceImpl;

/**
 * Special dto, the front end needs for the tendancy endpoint to
 * have info not only if it's increasing or not but also fares prices
 * of all journeys to draw a graph
 * @author mickaelgudin
 * @see JourneyServiceImpl#getTendancy(int, int, String)
 */
public class TendancyDto implements Serializable {
	private String tendancy;
	private List<Double> allFarePrices = new ArrayList<Double>();

	public TendancyDto(String tendancy, List<Double> allFarePrices) {
		super();
		this.tendancy = tendancy;
		this.allFarePrices = allFarePrices;
	}

	public String getTendancy() {
		return tendancy;
	}

	public void setTendancy(String tendancy) {
		this.tendancy = tendancy;
	}

	public List<Double> getAllFarePrices() {
		return allFarePrices;
	}

	public void setAllFarePrices(List<Double> allFarePrices) {
		this.allFarePrices = allFarePrices;
	}
}
