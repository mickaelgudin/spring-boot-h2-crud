package com.spring.crud.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.JourneyRepository;

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

	@Autowired
	private TrainStationService trainStationService;

	@Autowired
	private JourneyRepository journeyRepository;

	@GetMapping
	public List<TrainStation> getAll() {
		return trainStationService.getAll();
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody TrainStation trainStation) {
		TrainStation savedTrainStation = null;

		try {
			savedTrainStation = trainStationService.save(trainStation);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LanguageManager.get().getString("station.errorNew"));
		}

		return ResponseEntity.ok().body(savedTrainStation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody TrainStation trainStation) {
		String errorMessage = this.checkStation(id, "departure");
		if (errorMessage != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		TrainStation updatedTrainStation = trainStationService.update(id, trainStation);
		return ResponseEntity.ok().body(updatedTrainStation);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable int id) {
		String errorMessage = this.checkStation(id, "departure");
		if (errorMessage != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}

		journeyRepository.deleteJourneysOfStation(id);
		trainStationService.delete(id);
		return ResponseEntity.ok().body(LanguageManager.get().getString("station.deleted"));
	}

	/**
	 * check both stations for errors, return error 400 if an error was encountered
	 * @param idFrom
	 * @param idTo
	 * @see TrainStationController#checkStation(int, String)
	 */
	public void checkBothStationError(int idFrom, int idTo) {
		String errorMessage = "";

		String error = checkStation(idFrom, "departure");
		// checking if stations exist
		if (error != null) {
			errorMessage += error;
		}

		error = checkStation(idTo, "arrival");
		if (error != null) {
			errorMessage += !errorMessage.equals("") ? "\n" + error : error;
		}

		// throwing error if one of the station don't exist
		if (!errorMessage.equals("")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}
	}

	/**
	 * check if the station exist, if not we return a custom error depending on the type
	 * @param idStation
	 * @param typeStation
	 * @return message of error, if an error was detected
	 */
	public String checkStation(int idStation, String typeStation) {
		TrainStation station = trainStationService.checkIfStationExist(idStation);
		String errorMessage = null;

		if (station == null)
			errorMessage = LanguageManager.get().getString("station." + typeStation + ".dontexist");

		return errorMessage;
	}
}
