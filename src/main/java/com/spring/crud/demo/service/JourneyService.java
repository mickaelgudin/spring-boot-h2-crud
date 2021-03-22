package com.spring.crud.demo.service;


import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.TrainStation;

import java.util.List;

public interface JourneyService {

    List<Journey> getAll();

    String getTendancy(int stationDepart, int stationArrival);
    
    Journey save(Journey journey);
}
