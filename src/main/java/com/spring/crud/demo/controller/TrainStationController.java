package com.spring.crud.demo.controller;

import java.util.List;

import com.spring.crud.demo.model.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spring.crud.demo.service.TrainStationService;

@RestController
@RequestMapping("/train-stations")
public class TrainStationController {
	
	@Autowired
	private TrainStationService studentService;

	@GetMapping
	public List<TrainStation> getAll() {
		return studentService.getAll();
	}
	

	@GetMapping("/{id}")
	public TrainStation getStudentById(@PathVariable int id ) {
		return studentService.getTrainStationById(id);
	}
	
	// get Student by first name (equals())
	@GetMapping("/by-name/{name}")
	public TrainStation getOneStudentByFirstName(@PathVariable String name) {
		return studentService.getOneTrainStationByName(name);
	}
}




