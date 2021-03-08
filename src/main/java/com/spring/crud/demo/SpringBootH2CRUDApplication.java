package com.spring.crud.demo;

import com.spring.crud.demo.model.TrainStation;
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


@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class SpringBootH2CRUDApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2CRUDApplication.class, args);
	}



	@Autowired
	private TrainStationRepository trainStationRepository;
	
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			List<TrainStation> stations = trainStationRepository.findAll();
			if (stations.isEmpty()) {
				HelperUtil helper = new HelperUtil();
				trainStationRepository.saveAll(helper.getTrainsStations());
			}
		};
	}

}
