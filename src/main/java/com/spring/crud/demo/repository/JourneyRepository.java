package com.spring.crud.demo.repository;

import com.spring.crud.demo.model.Journey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JourneyRepository extends JpaRepository<Journey, Integer> {
	@Query("SELECT j FROM Journey j WHERE j.departureStation.trainStationId= :stationDepart AND j.arrivalStation.trainStationId= :stationArrival ORDER BY j.departureDate")
	List<Journey> getJourneysOfDestinations(@Param("stationDepart") int stationDepart, @Param("stationArrival") int stationArrival);
	
	
	@Query("SELECT j FROM Journey j WHERE j.departureStation.trainStationId= :stationDepart")
	List<Journey> getJourneysAverageByStation(@Param("stationDepart") int stationDepart);
	
	@Modifying
	@Query("DELETE FROM Journey j WHERE j.departureStation.trainStationId= :station OR j.arrivalStation.trainStationId= :station")
	void deleteJourneysOfStation(@Param("station") int station);
}