package com.spring.crud.demo.controller;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journeys")
/**
 * Rest controller for journeys
 * @author MGud
 */
public class JourneyController {

	@Autowired
	private JourneyService journeyService;
	
	@Autowired
	private TrainStationController stationController;

	@GetMapping
	public List<Journey> getAllJourneyWithStations(@RequestParam(name = "id-from") int idFrom,
			@RequestParam(name = "id-to") int idTo) throws ResponseStatusException {
		stationController.checkBothStationError(idFrom, idTo);

		return journeyService.getAllWithGivenStations(idFrom, idTo);
	}

	@GetMapping("/average")
	public Map<String, Double> getJourneysAverageByStation(@RequestParam(name = "id-from") int idFrom) {
		String error = stationController.checkStation(idFrom, "departure");
		if(error != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
		}
		
		return journeyService.getJourneysAverageByStation(idFrom);
	}

	@GetMapping("/tendancy")
	public String getTendancy(@RequestParam(name = "id-from") int idFrom, @RequestParam(name = "id-to") int idTo) {
		stationController.checkBothStationError(idFrom, idTo);

		return journeyService.getTendancy(idFrom, idTo);
	}
}
