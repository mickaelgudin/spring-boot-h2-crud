package com.spring.crud.demo.repository;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.TrainStation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JourneyRepository extends JpaRepository<Journey, Integer> {
	@Query("SELECT j FROM Journey j WHERE j.departureStation= :stationDepart AND j.arrivalStation= :stationArrival ORDER BY j.departureDate")
	List<Journey> getJourneysOfDestination(@Param("stationDepart") TrainStation stationDepart, @Param("stationArrival") TrainStation stationArrival);

}