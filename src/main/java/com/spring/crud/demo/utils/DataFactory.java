package com.spring.crud.demo.utils;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.LineTrainStation;
import com.spring.crud.demo.model.TrainStation;

import java.time.LocalDateTime;

/**
 * Class to init original data 
 * Useful because Heroku free plan delete H2 files(so data are deleted every 24 hours)
 * 
 * Calling Navitia Api from the back end to regenerate data 
 * would be time consuming
 * 
 * @author MGud
 */
public class DataFactory {
	
	
    public DataFactory() {
		super();
	}

	public TrainStation getTrainStationVersaillesChantiers() {
    	return new TrainStation("Gare de Versailles Chantiers", 2.135468, 48.795717);
    }
    
    public TrainStation getTrainStationMontparnasse() {
    	return new TrainStation("Gare Montparnasse", 2.319118, 48.841214);
    }
    
    public TrainStation getTrainStationLaDefense() {
    	return new TrainStation("Gare de la Défense (Grande Arche)", 2.239209, 48.891731);
    }

    public Journey getJourneyWithPrice(TrainStation depart, TrainStation arrivee, LocalDateTime startDate, double farePrice, LineTrainStation line) {
    	return new Journey(depart, arrivee, startDate, startDate.plusMinutes(20), farePrice, line);
    }
    
    public LineTrainStation getLineVersaillesToMontparnasse() {
        return new LineTrainStation("VersaillesToMontparnasse");
    }

    public LineTrainStation getLineWithGivenName(String name) {
    	return new LineTrainStation(name);
    }
    
    public void setLineforTrainStation(LineTrainStation line, TrainStation station) {
    	line.getTrainStations().add(station);
    	station.getLines().add(line);
    }
}
