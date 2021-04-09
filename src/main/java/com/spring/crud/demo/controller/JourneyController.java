package com.spring.crud.demo.controller;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.service.JourneyService;
import com.spring.demo.dto.TendancyDto;

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
 * @author NicolasLewin
 */
public class JourneyController {

	@Autowired
	private JourneyService journeyService;
	
	@Autowired
	private TrainStationController stationController;

	@GetMapping("/{langue}")
	public List<Journey> getAllJourneyWithStations(@RequestParam(name = "id-from") int idFrom,
			@RequestParam(name = "id-to") int idTo, @PathVariable String langue) throws ResponseStatusException {
		stationController.checkBothStationError(idFrom, idTo, langue);

		return journeyService.getAllWithGivenStations(idFrom, idTo);
	}

	@GetMapping("/{langue}/average")
	public Map<String, Double> getJourneysAverageByStation(@RequestParam(name = "id-from") int idFrom, @PathVariable String langue) {
		String error = stationController.checkStation(idFrom, "departure", langue);
		if(error != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
		}
		
		return journeyService.getJourneysAverageByStation(idFrom);
	}

	@GetMapping("/{langue}/tendancy")
	public TendancyDto getTendancy(@RequestParam(name = "id-from") int idFrom, @RequestParam(name = "id-to") int idTo, @PathVariable String langue) {
		stationController.checkBothStationError(idFrom, idTo, langue);

		return journeyService.getTendancy(idFrom, idTo, langue);
	}
}
