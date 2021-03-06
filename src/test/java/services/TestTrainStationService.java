package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.service.TrainStationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2CRUDApplication.class)
public class TestTrainStationService {
	@Autowired
	private TrainStationService stationService;

	@BeforeClass
    public static void beforeClass() throws IOException {
		InputStream contentStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("language_fr.properties");
	    ResourceBundle rb = new PropertyResourceBundle(contentStream);
	    LanguageManager.set(rb);
	    LanguageManager.testIsRunning = true;
	}
	
	@Test
	public void testGetAll() {
		// testing if original data are created at system start
		assertTrue(stationService.getAll().size() > 0);
	}

	@Test
	public void testSave() {
		TrainStation newStation = new TrainStation("test", 20.4, 20.2);
		TrainStation insertedStation = stationService.save(newStation);

		assertTrue(insertedStation != null && insertedStation.getName() == "test");
	}

	@Test
	public void testUpdate() {
		List<TrainStation> stations = stationService.getAll();
		TrainStation lastStation = stations.get(stations.size() - 1);
		lastStation.setName("testUpdateTest");

		TrainStation updatedStation = stationService.update(lastStation.getTrainStationId(), lastStation);
		assertEquals("testUpdateTest", updatedStation.getName());

	}

	@Test
	public void testDelete() {
		List<TrainStation> stations = stationService.getAll();
		TrainStation lastStation = stations.get(stations.size() - 1);

		stationService.delete(lastStation.getTrainStationId());
		
		List<TrainStation> updatedStations = stationService.getAll();
		
		assertNotEquals(updatedStations.size(), stations.size());
	}

}
