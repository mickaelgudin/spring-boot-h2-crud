package services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
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

	@Test
	public void testGetAllWithGivenStations() {
		List<TrainStation> stations = serviceStation.getAll();
		int trainStationDepartId = stations.get(0).getTrainStationId();
		int trainStationArrivalId = stations.get(2).getTrainStationId();

		List<Journey> allJourneys = serviceJourney.getAllWithGivenStations(trainStationDepartId, trainStationArrivalId);
		assertFalse(allJourneys.isEmpty());
	}
	
	@Test
	public void testGetJourneysAverageByStation() {
		List<TrainStation> stations = serviceStation.getAll();
		int trainStationDepartId = stations.get(0).getTrainStationId();

		Map<String, Double> avgPricesForStation = serviceJourney.getJourneysAverageByStation(trainStationDepartId);
		
		//in original data there are journeys for all stations so the map returned by the service can't be empty
		assertFalse(avgPricesForStation.isEmpty());
	}
	
	@Test
	public void testGetTendancy() {
		List<TrainStation> stations = serviceStation.getAll();
		int trainStationDepartId = stations.get(0).getTrainStationId();
		int trainStationArrivalId = stations.get(2).getTrainStationId();

		String tendancyPrice = serviceJourney.getTendancy(trainStationDepartId, trainStationArrivalId);
		
		String decreasing = LanguageManager.languageSelected.getString("tendancy.decreasing");
		String increasing = LanguageManager.languageSelected.getString("tendancy.decreasing");
		String stable = LanguageManager.languageSelected.getString("tendancy.stable");
		
		//in original data there are journey for all stations - so there is a tendancy (stable, incresing, decreasing)
		assertTrue(tendancyPrice.equals(stable) || tendancyPrice.equals(increasing) || tendancyPrice.equals(decreasing));
	}
}
