package com.spring.crud.demo.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.transaction.Transactional;

import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.JourneyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		TrainStation updatedTrainStation = trainStationService.update(id, trainStation);
        return ResponseEntity.ok().body(updatedTrainStation);
    }
	
	@DeleteMapping("/{id}")
	@Transactional
    public ResponseEntity<?> delete(@PathVariable int id) {
		journeyRepository.deleteJourneysOfStation(id);
		trainStationService.delete(id);
        return ResponseEntity.ok().body(LanguageManager.languageSelected.getString("station.deleted"));
    }
}
