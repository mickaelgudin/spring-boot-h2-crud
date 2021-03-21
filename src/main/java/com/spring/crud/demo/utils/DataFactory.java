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

    public Journey getJourney10() {
        return new Journey(getTrainStationVersaillesChantiers(), getTrainStationMontparnasse(), LocalDateTime.now(), LocalDateTime.now(), 10);
    }

    public Journey getJourney20() {
        return new Journey(getTrainStationVersaillesChantiers(), getTrainStationMontparnasse(), LocalDateTime.now(), LocalDateTime.now(), 20);
    }

    public Journey getJourney30() {
        return new Journey(getTrainStationVersaillesChantiers(), getTrainStationMontparnasse(), LocalDateTime.now(), LocalDateTime.now(), 30);
    }

    public Journey getJourney40() {
        return new Journey(getTrainStationVersaillesChantiers(), getTrainStationMontparnasse(), LocalDateTime.now(), LocalDateTime.now(), 40);
    }

    public Journey getJourney50() {
        return new Journey(getTrainStationVersaillesChantiers(), getTrainStationMontparnasse(), LocalDateTime.now(), LocalDateTime.now(), 50);
    }

    public LineTrainStation getLineVersaillesToMontparnasse() {
        return new LineTrainStation("VersaillesToMontparnasse");
    }

    public LineTrainStation getLineN() {
    	return new LineTrainStation("N");
    }
    
    public void setLineforTrainStation(LineTrainStation line, TrainStation station) {
    	line.getTrainStations().add(station);
    	station.getLines().add(line);
    }
}
