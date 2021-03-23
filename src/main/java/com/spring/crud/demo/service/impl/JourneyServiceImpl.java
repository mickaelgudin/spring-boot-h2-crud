package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.JourneyRepository;
import com.spring.crud.demo.repository.TrainStationRepository;
import com.spring.crud.demo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyServiceImpl implements JourneyService {

	@Autowired
	private JourneyRepository repository;
	@Autowired
	private TrainStationRepository repositoryStation;

	@Override
	public List<Journey> getAllWithGivenStations(int idDepartStation, int idArrivalStation) {
		return repository.getJourneysOfDestination(idDepartStation, idDepartStation);
	}

	@Override
	public Journey save(Journey journey) {
		return repository.save(journey);
	}

	private TrainStation checkIfStationExist(int idStation) {
		Optional<TrainStation> stationOptionnal = repositoryStation.findById(idStation);
		if (stationOptionnal.isPresent()) {
			return stationOptionnal.get();
		}

		return null;
	}

	@Override
	public String getTendancy(int idStationDepart, int idStationArrival) {
		TrainStation departStation = checkIfStationExist(idStationDepart);
		if (departStation == null)
			return "departure station doesn't exist";

		TrainStation arrivalStation = checkIfStationExist(idStationArrival);
		if (arrivalStation == null)
			return "arrival station doesn't exist";

		List<Journey> filteredJourneys = repository.getJourneysOfDestination(idStationDepart, idStationArrival);
		if (filteredJourneys.isEmpty()) {
			return "no data yet";
		}

		return determineTendancy(filteredJourneys);
	}

	private String determineTendancy(List<Journey> filteredJourneys) {
		// defining a precise tendancy if the given journey has at least 2 records
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
