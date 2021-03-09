package com.spring.crud.demo.service;


import java.util.List;

import com.spring.crud.demo.model.TrainStation;

public interface TrainStationService {
	
	List<TrainStation> getAll();

	TrainStation getTrainStationById(int stationId);

	TrainStation save(TrainStation trainStation);
	
	TrainStation update(int id, TrainStation trainStation);

    void delete(int id);
}
