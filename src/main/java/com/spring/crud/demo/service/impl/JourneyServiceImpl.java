package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.spring.crud.demo.config.LanguageManager;
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
		List<Journey> journeys = repository.getJourneysOfDestinations(idDepartStation, idArrivalStation);

		return journeys;
	}

	@Override
	public Journey save(Journey journey) {
		return repository.save(journey);
	}


	@Override
	public String getTendancy(int idStationDepart, int idStationArrival) {
		List<Journey> filteredJourneys = repository.getJourneysOfDestinations(idStationDepart, idStationArrival);
		if (filteredJourneys.isEmpty()) {
			return LanguageManager.get().getString("journey.nodata");
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
				return LanguageManager.get().getString("tendancy.decreasing");

			else if (balance > 0)
				return LanguageManager.get().getString("tendancy.increasing");
		}

		return LanguageManager.get().getString("tendancy.stable");
	}

	@Override
	public Map<String, Double> getJourneysAverageByStation(int idDepartStation) {
		List<Journey> journeysFromStation = repository.getJourneysAverageByStation(idDepartStation);

		return this.computeAveragePricesByStation(journeysFromStation);
	}

	private Map<String, Double> computeAveragePricesByStation(List<Journey> journeysFromStation) {
		Map<String, Double> avgPriceByArrivalStation = new TreeMap<String, Double>();
		Map<String, Integer> occurencesByStationId = new TreeMap<String, Integer>();

		// sum of fare prices by stations
		for (Journey j : journeysFromStation) {
			String currentArrivalStation = j.getArrivalStation().getName();

			if (!avgPriceByArrivalStation.containsKey(currentArrivalStation)) {
				avgPriceByArrivalStation.put(currentArrivalStation, 0.0);
			}
			if (!occurencesByStationId.containsKey(currentArrivalStation)) {
				occurencesByStationId.put(currentArrivalStation, 0);
			}

			// update adding current fare price to the arrival station sum
			avgPriceByArrivalStation.merge(currentArrivalStation, j.getFarePrice(), Double::sum);
			occurencesByStationId.merge(currentArrivalStation, 1, Integer::sum);
		}

		// compute averages of fare prices by stations
		for (String key : avgPriceByArrivalStation.keySet()) {
			int nbOfOccurence = occurencesByStationId.get(key);
			double sommeFarePrices = avgPriceByArrivalStation.get(key);

			avgPriceByArrivalStation.put(key, sommeFarePrices / nbOfOccurence);
		}

		return avgPriceByArrivalStation;
	}

}
