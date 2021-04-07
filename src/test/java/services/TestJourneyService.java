package services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.crud.demo.SpringBootH2CRUDApplication;
import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.service.JourneyService;
import com.spring.crud.demo.service.TrainStationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2CRUDApplication.class)
public class TestJourneyService {
	@Autowired
	private JourneyService serviceJourney;
	@Autowired
	private TrainStationService serviceStation;

	
	@BeforeClass
    public static void beforeClass() throws IOException {
		InputStream contentStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("language_fr.properties");
	    ResourceBundle languagePropTest = new PropertyResourceBundle(contentStream);
	    LanguageManager.set(languagePropTest);
	    LanguageManager.testIsRunning = true;
	}
	
	@Test
	public void testGetAllWithGivenStations() {
		List<TrainStation> stations = serviceStation.getAll();
		int trainStationDepartId = stations.get(0).getTrainStationId();
		int trainStationArrivalId = stations.get(2).getTrainStationId();

		List<Journey> allJourneys = serviceJourney.getAllWithGivenStations(trainStationDepartId, trainStationArrivalId);
		assertFalse("journeys return by service not empty", allJourneys.isEmpty());
	}
	
	@Test
	public void testGetJourneysAverageByStation() {
		List<TrainStation> stations = serviceStation.getAll();
		int trainStationDepartId = stations.get(0).getTrainStationId();

		Map<String, Double> avgPricesForStation = serviceJourney.getJourneysAverageByStation(trainStationDepartId);
		
		//in original data there are journeys for all stations
		assertFalse("at least of avg prices was determine", avgPricesForStation.isEmpty());
	}
	
	@Test
	public void testGetTendancy() {
		List<TrainStation> stations = serviceStation.getAll();
		int trainStationDepartId = stations.get(0).getTrainStationId();
		int trainStationArrivalId = stations.get(2).getTrainStationId();

		String tendancyPrice = serviceJourney.getTendancy(trainStationDepartId, trainStationArrivalId, "fr");
		
		//in original data there are journey for all stations - so there is a tendancy (stable, incresing, decreasing)
		assertTrue("a tendancy was determine", tendancyPrice.equals("stable") || tendancyPrice.equals("up") || tendancyPrice.equals("down"));
		
	}
}
