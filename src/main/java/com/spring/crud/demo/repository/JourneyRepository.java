package com.spring.crud.demo.repository;

import com.spring.crud.demo.model.Journey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JourneyRepository extends JpaRepository<Journey, Integer> {
	@Query("SELECT j FROM Journey j WHERE j.departureStation.trainStationId= :stationDepart AND j.arrivalStation.trainStationId= :stationArrival ORDER BY j.departureDate")
	List<Journey> getJourneysOfDestination(@Param("stationDepart") int stationDepart,
			@Param("stationArrival") int stationArrival);
}