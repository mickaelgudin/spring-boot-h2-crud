package services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.crud.demo.SpringBootH2CRUDApplication;
import com.spring.crud.demo.model.TrainStation;
import com.spring.crud.demo.repository.TrainStationRepository;
import com.spring.crud.demo.service.TrainStationService;
import com.spring.crud.demo.service.impl.TrainStationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootH2CRUDApplication.class)
public class TestTrainStationService {
	@Autowired
    private TrainStationService stationService;
	
	
	@Test
	public void testGetAll() {
		//testing if original data are created at system start
		assertTrue(stationService.getAll().size() > 0);
	}
	
	@Test
	public void testSave() {
		TrainStation newStation = new TrainStation("test", 20.4, 20.2);
		TrainStation insertedStation = stationService.save(newStation);
		
		assertTrue(insertedStation != null && insertedStation.getName() == "test");
	}
	
	@Test
	public void testGetTrainStationById() {
		List<TrainStation> stations = stationService.getAll();
		int lastIdOfStations = stations.get(stations.size()-1).getTrainStationId();
		TrainStation lastStation = stationService.getTrainStationById(lastIdOfStations);
		
		assertEquals(lastIdOfStations, lastStation.getTrainStationId());
		
		//trying to get non existing station - should return null
		assertNull(stationService.getTrainStationById(15550));
	}
	
	@Test
	public void testUpdate() {
		List<TrainStation> stations = stationService.getAll();
		TrainStation lastStation = stations.get(stations.size()-1);
		lastStation.setName("testUpdate");
		
		TrainStation updatedStation = stationService.update(lastStation.getTrainStationId(), lastStation);
		assertEquals("testUpdate", updatedStation.getName());
		
		//trying to update non existing station - should return null
		assertNull(stationService.update(15550, new TrainStation()) );
	}
	
	@Test
	public void testDelete() {
		List<TrainStation> stations = stationService.getAll();
		TrainStation lastStation = stations.get(stations.size()-1);
		
		stationService.delete(lastStation.getTrainStationId());
		
		//trying to get deleted station - should return null
		assertNull( stationService.getTrainStationById(lastStation.getTrainStationId())  );
	}

}
