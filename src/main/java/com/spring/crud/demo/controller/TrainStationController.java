package com.spring.crud.demo.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
		TrainStation savedTrainStation = trainStationService.save(trainStation);
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
        return ResponseEntity.ok().body(LanguageManager.languageSelected.getString("station.deleted"));
    }
	
	public void checkBothStationError(int idFrom, int idTo) {
		String errorMessage = "";

		String error = checkStation(idFrom, "departure");
		// checking if stations exist
		if (error != null) {
			errorMessage += error;
		}
		
		error = checkStation(idTo, "arrival");
		if (error != null) {
			errorMessage += !errorMessage.equals("") ? "\n"+error : error;
		}

		// throwing error if one of the station don't exist
		if (!errorMessage.equals("")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}
	}
	
	public String checkStation(int idStation, String typeStation) {
		TrainStation station = trainStationService.checkIfStationExist(idStation);
		String errorMessage = null;

		if (station == null)
			errorMessage = LanguageManager.languageSelected.getString("station." + typeStation + ".dontexist");

		return errorMessage;
	}
}
