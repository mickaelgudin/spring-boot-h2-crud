package com.spring.crud.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.spring.crud.demo.model.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.crud.demo.repository.TrainStationRepository;
import com.spring.crud.demo.service.TrainStationService;


/**
 * service layer for train station containing all the business logic for train stations
 * @see TrainStationService inherited java doc
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
		return em.merge(updatedStation);
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
