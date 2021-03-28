package com.spring.crud.demo;

import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.JourneyRepository;
import com.spring.crud.demo.repository.LineTrainStationRepository;
import com.spring.crud.demo.repository.TrainStationRepository;
import com.spring.crud.demo.utils.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class SpringBootH2CRUDApplication {
	

	public static void main(String[] args) {
		LanguageManager.get();
		SpringApplication.run(SpringBootH2CRUDApplication.class, args);
	}
	

	@Autowired
	private TrainStationRepository trainStationRepository;
	@Autowired
	private JourneyRepository journeyRepository;
	
	
	//inserting original data at system start
	@Bean
	CommandLineRunner runner() {
		return args -> {
			List<TrainStation> stations = trainStationRepository.findAll();
			if (stations.isEmpty()) {
				HelperUtil helper = new HelperUtil();
				List<TrainStation> insertedStations = trainStationRepository.saveAll(helper.getTrainsStations() );
				stations.addAll(insertedStations);
			}
			
			List<Journey> journeys = journeyRepository.findAll();
			if(journeys.isEmpty()) {
				HelperUtil helper = new HelperUtil();
				TrainStation versailles = stations.get(0);
				TrainStation montparnasse = stations.get(1);
				TrainStation laDefense = stations.get(2);
				
				journeys.addAll(helper.getJourneysWithIncreasingPrice(versailles, montparnasse,  montparnasse.getLines().get(0)) );
				journeys.addAll(helper.getJourneysWithDecreasingPrice(versailles, laDefense, laDefense.getLines().get(0)) );
				journeys.addAll(helper.getJourneysWithIncreasingPrice(montparnasse, versailles,  montparnasse.getLines().get(0)) );
				journeys.addAll(helper.getJourneysWithStablePrice(laDefense, versailles, laDefense.getLines().get(0)) );
				
				journeyRepository.saveAll(journeys);
			}
		};
	}

}
