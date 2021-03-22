package com.spring.crud.demo.service.impl;

import java.util.List;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.JourneyRepository;
import com.spring.crud.demo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyServiceImpl implements JourneyService {

	@Autowired
	private JourneyRepository repository;

	@Override
	public List<Journey> getAll() {
		return repository.findAll();
	}

	@Override
	public Journey save(Journey journey) {
		return repository.save(journey);
	}

	
	@Override
	public String getTendancy(int idStationDepart, int idStationArrival) {
		TrainStation departStation = entityManager.find(TrainStation.class, idStationDepart);
		TrainStation arrivalStation = entityManager.find(TrainStation.class, idStationArrival);
		
		List<Journey> filteredJourneys = repository.getJourneysOfDestination(departStation, arrivalStation);

		if(filteredJourneys.isEmpty()) {
			return "no data yet";
		}
		
		//defining a precise tendancy if the given journey has at least 2 records
		if (filteredJourneys.size() >= 2) {
			Journey firstJourney = filteredJourneys.get(0);
			Journey lastJourney = filteredJourneys.get(filteredJourneys.size() - 1);

			double balance = lastJourney.getFarePrice() - firstJourney.getFarePrice();

			if (balance < 0)
				return "decreasing";

			else if (balance > 0)
				return "increasing";
		}

		return "stable";
	}

}
