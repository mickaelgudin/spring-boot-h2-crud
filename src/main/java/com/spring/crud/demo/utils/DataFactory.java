package com.spring.crud.demo.utils;

import com.spring.crud.demo.model.LineTrainStation;
import com.spring.crud.demo.model.TrainStation;

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
    
    public LineTrainStation getLineN() {
    	return new LineTrainStation("N");
    }
    
    public void setLineforTrainStation(LineTrainStation line, TrainStation station) {
    	line.getTrainStations().add(station);
    	station.getLines().add(line);
    }
}
