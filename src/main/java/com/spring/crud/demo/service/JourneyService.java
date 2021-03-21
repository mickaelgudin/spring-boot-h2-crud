package com.spring.crud.demo.service;


import com.spring.crud.demo.model.Journey;

import java.util.List;

public interface JourneyService {

    List<Journey> getAll();

    Journey save(Journey journey);


}
