package com.spring.crud.demo.controller;

import java.util.List;

import com.spring.crud.demo.model.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.crud.demo.service.TrainStationService;

@RestController
@RequestMapping("/train-stations")
public class TrainStationController {
	
	@Autowired
	private TrainStationService trainStationService;

	@GetMapping
	public List<TrainStation> getAll() {
		return trainStationService.getAll();
	}
	

	@GetMapping("/{id}")
	public TrainStation getStudentById(@PathVariable int id ) {
		return trainStationService.getTrainStationById(id);
	}
	
	@GetMapping("/by-name/{name}")
	public TrainStation getOneStudentByFirstName(@PathVariable String name) {
		return trainStationService.getOneTrainStationByName(name);
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
    public ResponseEntity<?> delete(@PathVariable int id) {
		trainStationService.delete(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }
}
