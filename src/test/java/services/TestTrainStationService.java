package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.spring.crud.demo.SpringBootH2CRUDApplication;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.service.TrainStationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2CRUDApplication.class)
public class TestTrainStationService {
	@Autowired
	private TrainStationService stationService;

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
		lastStation.setName("testUpdate");

		TrainStation updatedStation = stationService.update(lastStation.getTrainStationId(), lastStation);
		assertEquals("testUpdate", updatedStation.getName());

	}

	@Test
	public void testDelete() {
		List<TrainStation> stations = stationService.getAll();
		TrainStation lastStation = stations.get(stations.size() - 1);

		stationService.delete(lastStation.getTrainStationId());
		
		List<TrainStation> updatedStations = stationService.getAll();
		
		assertTrue(updatedStations.size() != stations.size());
	}

}
