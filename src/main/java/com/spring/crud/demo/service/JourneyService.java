package com.spring.crud.demo.service;

import com.spring.crud.demo.model.Journey;
import com.spring.demo.dto.TendancyDto;

import java.util.List;
import java.util.Map;

/**
 * service layer interface for journey
 * @author NicolasLewin
 */
public interface JourneyService {

	/**
	 * get tendancy of journey fare prices between the given stations
	 * @param stationDepart
	 * @param stationArrival
	 * @param langue
	 * @see TendancyDto
	 * @return tendancy and fare prices
	 */
	TendancyDto getTendancy(int stationDepart, int stationArrival, String langue);

	/**
	 * save the new journey
	 * @param journey entity to save
	 * @return saved journey
	 */
	Journey save(Journey journey);

	/**
	 * get all journeys between the two given stations
	 * @param idDepartStation
	 * @param idArrivalStation
	 * @return all journeys without criteria of date between the given station
	 */
	List<Journey> getAllWithGivenStations(int idDepartStation, int idArrivalStation);

	/**
	 * get all reachable stations from the given station of departure
	 * and their associated average fare price
	 * @param idDepartStation station of departure
	 * @see JourneyServiceImpl#computeAveragePricesByStation(List)
	 * @return a map with all the names of the reachable stations of arrival and
	 *         their associated average fare price
	 */
	Map<String, Double> getJourneysAverageByStation(int idDepartStation);

}
