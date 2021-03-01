package com.spring.crud.demo.service;


import java.util.List;

import com.spring.crud.demo.model.TrainStation;

public interface TrainStationService {
	
	List<TrainStation> getAll();

	TrainStation getOneTrainStationByName(String name);

	TrainStation getTrainStationById(int empId);
	
}
