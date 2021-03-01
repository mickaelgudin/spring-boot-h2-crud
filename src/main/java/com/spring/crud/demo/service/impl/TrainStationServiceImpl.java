package com.spring.crud.demo.service.impl;

import java.util.List;
import com.spring.crud.demo.model.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crud.demo.repository.TrainStationRepository;
import com.spring.crud.demo.service.TrainStationService;

@Service
public class TrainStationServiceImpl implements TrainStationService {

	@Autowired
	private TrainStationRepository repository;

	@Override
	public List<TrainStation> getAll() {
		return repository.findAll();
	}

	@Override
	public TrainStation getOneTrainStationByName(String name) {

		List<TrainStation> students = repository.findAll();

		for (TrainStation emp : students) {
			if (emp.getName().equalsIgnoreCase(name))
				return emp;
		}
		return null;
	}

	@Override
	public TrainStation getTrainStationById(int empId) {
		List<TrainStation> students = repository.findAll();
		for (TrainStation emp : students) {
			if (empId == emp.getId())
				return emp;
		}
		return null;
	}

}
