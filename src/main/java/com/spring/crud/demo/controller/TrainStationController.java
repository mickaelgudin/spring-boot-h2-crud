package com.spring.crud.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.JourneyRepository;
import com.spring.crud.demo.repository.TrainStationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.spring.crud.demo.service.TrainStationService;

@RestController
@RequestMapping("/train-stations")
/**
 * Rest controller for train stations
 * @author mickaelgudin
 */
public class TrainStationController {
	static final String DEPARTURE_TYPE = "departure";
	
	@Autowired
	private TrainStationService trainStationService;

	@Autowired
	private TrainStationRepository stationRepository;
	
	@Autowired
	private JourneyRepository journeyRepository;

	@GetMapping
	public List<TrainStation> getAll() {
		return trainStationService.getAll();
	}

	@PostMapping("/{langue}")
	public ResponseEntity<?> save(@RequestBody TrainStation trainStation, @PathVariable String langue) {
		TrainStation savedTrainStation = null;

		try {
			savedTrainStation = trainStationService.save(trainStation);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LanguageManager.get(langue).getString("station.errorNew"));
		}

		return ResponseEntity.ok().body(savedTrainStation);
	}
	
	@PostMapping("all/{langue}")
	public String saveAll(@RequestBody List<TrainStation> trainStations, @PathVariable String langue) {

		try {
			stationRepository.saveAll(trainStations);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LanguageManager.get(langue).getString("station.errorNew"));
		}

		return "ok";
	}

	@PutMapping("/{langue}/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody TrainStation trainStation, @PathVariable String langue) {
		String errorMessage = this.checkStation(id, DEPARTURE_TYPE, langue);
		if (errorMessage != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}
		
		TrainStation mergedStation = this.mergeAndCheckStation(trainStation, id, langue);
		TrainStation updatedTrainStation = trainStationService.update(id, mergedStation);
		return ResponseEntity.ok().body(updatedTrainStation);
	}
	
	public TrainStation mergeAndCheckStation(TrainStation trainStation, int id, String langue) {
		TrainStation oldStation = trainStationService.checkIfStationExist(id);
		boolean hasToBeUpdated = false;
		
		String newName = trainStation.getName();
		if(newName!= null && !newName.trim().isEmpty() && !oldStation.getName().equals(newName) ) {
			hasToBeUpdated = true;
			oldStation.setName(newName);
		}
		double newLongitude = (trainStation.getLongitude() == null) ? 0 : trainStation.getLongitude();
		if(newLongitude != 0 && oldStation.getLongitude() != newLongitude) {
			hasToBeUpdated = true;
			oldStation.setLongitude(newLongitude);
		}
		double newLatitude = (trainStation.getLatitude() == null) ? 0 : trainStation.getLatitude();
		if(newLatitude != 0 && oldStation.getLatitude() != newLatitude) {
			hasToBeUpdated = true;
			oldStation.setLatitude(newLatitude);
		}
		
		//throw error if none of the following fields were modified : name, longitude, latitude
		if( !hasToBeUpdated) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LanguageManager.get(langue).getString("station.notupdatable"));
		}
		
		return oldStation;
	}
	

	@DeleteMapping("/{langue}/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable int id, @PathVariable String langue) {
		String errorMessage = this.checkStation(id, DEPARTURE_TYPE, langue);
		if (errorMessage != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		journeyRepository.deleteJourneysOfStation(id);
		trainStationService.delete(id);
		return ResponseEntity.ok().body(LanguageManager.get(langue).getString("station.deleted"));
	}

	/**
	 * check both stations for errors, return error 400 if an error was encountered
	 * @param idFrom
	 * @param idTo
	 * @see TrainStationController#checkStation(int, String)
	 * @return true if the two stations exists
	 */
	public Boolean checkBothStationError(int idFrom, int idTo, String langue) {
		String errorMessage = "";

		String error = checkStation(idFrom, DEPARTURE_TYPE, langue);
		// checking if stations exist
		if (error != null) {
			errorMessage += error;
		}

		error = checkStation(idTo, "arrival", langue);
		if (error != null) {
			errorMessage += !errorMessage.equals("") ? "\n" + error : error;
		}

		// throwing error if one of the station don't exist
		if (!errorMessage.equals("")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}
		return true;
	}

	/**
	 * check if the station exist, if not we return a custom error depending on the type
	 * @param idStation
	 * @param typeStation
	 * @return message of error, if an error was detected
	 */
	public String checkStation(int idStation, String typeStation, String langue) {
		TrainStation station = trainStationService.checkIfStationExist(idStation);
		String errorMessage = null;

		if (station == null)
			errorMessage = LanguageManager.get(langue).getString("station." + typeStation + ".dontexist");

		return errorMessage;
	}
}
