package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.crud.demo.SpringBootH2CRUDApplication;
import com.spring.crud.demo.config.LanguageManager;
import com.spring.crud.demo.controller.TrainStationController;
import com.spring.crud.demo.model.TrainStation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2CRUDApplication.class)
public class TestTrainStationController {
	@Autowired
	private TrainStationController controller;

	@Test
	public void testCheckStation() {
		String errorMessage = controller.checkStation(0, "departure");
		assertEquals(LanguageManager.get().getString("station.departure.dontexist"), errorMessage);
	}
	
	@Test
	public void testGetAll() {
		List<TrainStation> stations = controller.getAll();
		assertTrue(stations.size() > 0);
	}
	
	@Test
	public void testSave() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		ResponseEntity<?> newStation = controller.save(station);
		TrainStation insertedStation = (TrainStation) newStation.getBody();
		
		assertEquals("test", insertedStation.getName());
	}
	
	@Test
	public void testDelete() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		ResponseEntity<?> newStation = controller.save(station);
		TrainStation insertedStation = (TrainStation) newStation.getBody();
		
		ResponseEntity<?> response = controller.delete(insertedStation.getTrainStationId());
		String messageDeleted = (String) response.getBody();
		
		assertEquals(messageDeleted, LanguageManager.get().getString("station.deleted"));
	}

	@Test
	public void testUpdate() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		ResponseEntity<?> newStation = controller.save(station);
		TrainStation insertedStation = (TrainStation) newStation.getBody();
		
		insertedStation.setName("test Update");
		
		//updating new station
		ResponseEntity<?> response = controller.update(insertedStation.getTrainStationId(), insertedStation);
		TrainStation updatedStation = (TrainStation) response.getBody();
		
		assertEquals("test Update", updatedStation.getName());
	}
}
