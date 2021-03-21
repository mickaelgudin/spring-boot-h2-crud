package com.spring.crud.demo.controller;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journeys")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @GetMapping
    public List<Journey> getAll() {
        return journeyService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Journey journey) {
        Journey savedTrainStation = journeyService.save(journey);
        return ResponseEntity.ok().body(savedTrainStation);
    }

}
