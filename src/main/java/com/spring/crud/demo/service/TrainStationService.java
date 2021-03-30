package com.spring.crud.demo.service;


import java.util.List;

import com.spring.crud.demo.model.TrainStation;

public interface TrainStationService {
	/**
	 * get all stations from database
	 * @return a list of all existing stations
	 */
	List<TrainStation> getAll();

	/**
	 * save a new station
	 * @param trainStation entity to save
	 * @return entity saved
	 */
	TrainStation save(TrainStation trainStation);
	
	/**
	 * update the given station
	 * @param id
	 * @param trainStation
	 * @return the updated entity
	 */
	TrainStation update(int id, TrainStation trainStation);

	/**
	 * delete the station having the given id
	 * @param id
	 */
    void delete(int id);
    
    /**
	 * determine if the given id matches a train station record
	 * @param idStation the id of the train station to check
	 * @return existing entity or null
	 */
    TrainStation checkIfStationExist(int idStation);
}
