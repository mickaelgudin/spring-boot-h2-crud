package com.spring.crud.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.repository.JourneyRepository;
import com.spring.crud.demo.service.JourneyService;
import com.spring.demo.dto.TendancyDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


/**
 * service layer for journey containing all the business logic of journeys
 * @see JourneyService inherited java doc
 * @author NicolasLewin
 */
@Service
public class JourneyServiceImpl implements JourneyService {

	@Autowired
	private JourneyRepository repository;

	@Override
	public List<Journey> getAllWithGivenStations(int idDepartStation, int idArrivalStation) {
		return repository.getJourneysOfDestinations(idDepartStation, idArrivalStation);
	}

	@Override
	public Journey save(Journey journey) {
		return repository.save(journey);
	}


	@Override
	public TendancyDto getTendancy(int idStationDepart, int idStationArrival, String langue) {
		List<Journey> filteredJourneys = repository.getJourneysOfDestinations(idStationDepart, idStationArrival);
		if (filteredJourneys == null || filteredJourneys.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LanguageManager.get(langue).getString("journey.nodata"));
		}

		return determineTendancy(filteredJourneys);
	}

	/**
	 * determine tendancy with soustraction of first and last journey
	 * here there is hard coded text because we won't use them in the front end
	 * @param filteredJourneys journeys of two train stations
	 * @return tendancy and all fares prices for the given journeys
	 */
	private TendancyDto determineTendancy(List<Journey> filteredJourneys) {
		// defining a precise tendancy if the given journey has at least 2 records
		if (filteredJourneys.size() >= 2) {
			Journey firstJourney = filteredJourneys.get(0);
			Journey lastJourney = filteredJourneys.get(filteredJourneys.size() - 1);

			double balance = lastJourney.getFarePrice() - firstJourney.getFarePrice();
			
			List<Double> farePrices = new ArrayList<Double>();
			for(Journey j: filteredJourneys) {
				farePrices.add(j.getFarePrice());
			}

			if (balance < 0)
				return new TendancyDto("down", farePrices);

			else if (balance > 0)
				return new TendancyDto("up", farePrices);
		}

		//setting the only available journey price
		List<Double> farePrices = new ArrayList<Double>();
		farePrices.add(filteredJourneys.get(0).getFarePrice());
		return new TendancyDto("stable", farePrices);
	}

	@Override
	public Map<String, Double> getJourneysAverageByStation(int idDepartStation) {
		List<Journey> journeysFromStation = repository.getJourneysAverageByStation(idDepartStation);
		return this.computeAveragePricesByStation(journeysFromStation);
	}

	/**
	 * determine the average fare price for all accessible destinations from of given station
	 * @param journeysFromStation all journey of a station
	 * @return a map with all the names of the accessible stations of arrival and their associated average fare price
	 */
	private Map<String, Double> computeAveragePricesByStation(List<Journey> journeysFromStation) {
		Map<String, Double> avgPriceByArrivalStation = new TreeMap<>();
		Map<String, Integer> occurencesByStationId = new TreeMap<>();

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
