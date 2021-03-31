package com.spring.crud.demo.service;

import com.spring.crud.demo.model.Journey;

import java.util.List;
import java.util.Map;

/**
 * service layer interface for journey
 * 
 * @author NicolasLewin
 */
public interface JourneyService {

	/**
	 * get tendancy of journey fare prices between the given stations
	 * 
	 * @param stationDepart
	 * @param stationArrival
	 * @return tendancy of fare prices(up, down, stable)
	 */
	String getTendancy(int stationDepart, int stationArrival);

	/**
	 * save the new journey
	 * 
	 * @param journey entity to save
	 * @return saved journey
	 */
	Journey save(Journey journey);

	/**
	 * get all journeys between the two given stations
	 * 
	 * @param idDepartStation
	 * @param idArrivalStation
	 * @return all journeys without criterias of date between the given station
	 */
	List<Journey> getAllWithGivenStations(int idDepartStation, int idArrivalStation);

	/**
	 * get all accessibles destinations and their associated fare price
	 * 
	 * @param idDepartStation station of departure
	 * @see JourneyServiceImpl#computeAveragePricesByStation(List)
	 * @return a map with all the names of the accessible stations of arrival and
	 *         their associated average fare price
	 */
	Map<String, Double> getJourneysAverageByStation(int idDepartStation);

}
