package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spring.crud.demo.repository.TrainStationRepository;
import com.spring.crud.demo.service.JourneyService;
import com.spring.crud.demo.service.TrainStationService;


/**
 * service layer for train station containing all the business logic
 * 
 * @see JourneyService inherited java doc
 * @author mickaelgudin
 */
@Service
@Transactional
public class TrainStationServiceImpl implements TrainStationService {

	@Autowired
	private TrainStationRepository repository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TrainStation> getAll() {
		return repository.findAll();
	}

	@Override
	public TrainStation save(TrainStation trainStation) {
		return repository.save(trainStation);
	}

	@Override
	public TrainStation update(int id, TrainStation updatedStation) {
		TrainStation oldStation = this.checkIfStationExist(id);
		boolean hasToBeUpdated = false;
		
		String newName = updatedStation.getName();
		if(newName!= null && newName != "" && !oldStation.getName().equals(newName) ) {
			hasToBeUpdated = true;
			oldStation.setName(newName);
		}
		double newLongitude = (updatedStation.getLongitude() == null) ? 0 : updatedStation.getLongitude();
		if(newLongitude != 0 && oldStation.getLongitude() != newLongitude) {
			hasToBeUpdated = true;
			oldStation.setLongitude(newLongitude);
		}
		double newLatitude = (updatedStation.getLatitude() == null) ? 0 : updatedStation.getLatitude();
		if(newLatitude != 0 && oldStation.getLatitude() != newLatitude) {
			hasToBeUpdated = true;
			oldStation.setLongitude(newLatitude);
		}
		
		//throw error if none of the following fields were modified : name, longitude, latitude
		if( !hasToBeUpdated) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LanguageManager.get().getString("station.notupdatable"));
		}
		
		
        return em.merge(oldStation);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}
	
	@Override
	public TrainStation checkIfStationExist(int idStation) {
		Optional<TrainStation> stationOptionnal = repository.findById(idStation);
		if (stationOptionnal.isPresent()) {
			return stationOptionnal.get();
		}

		return null;
	}

}
