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
		String errorMessage = controller.checkStation(0, "departure", "fr");
		assertEquals(LanguageManager.get("fr").getString("station.departure.dontexist"), errorMessage);
	}
	
	@Test
	public void testGetAll() {
		List<TrainStation> stations = controller.getAll();
		assertTrue(stations.size() > 0);
	}
	
	@Test
	public void testSave() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		ResponseEntity<?> newStation = controller.save(station, "fr");
		TrainStation insertedStation = (TrainStation) newStation.getBody();
		
		assertEquals("test", insertedStation.getName());
	}
	
	@Test
	public void testDelete() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		ResponseEntity<?> newStation = controller.save(station, "fr");
		TrainStation insertedStation = (TrainStation) newStation.getBody();
		
		ResponseEntity<?> response = controller.delete(insertedStation.getTrainStationId(), "fr");
		String messageDeleted = (String) response.getBody();
		
		assertEquals(messageDeleted, LanguageManager.get("fr").getString("station.deleted"));
	}

	@Test
	public void testUpdate() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		ResponseEntity<?> newStation = controller.save(station, "fr");
		TrainStation insertedStation = (TrainStation) newStation.getBody();
		
		insertedStation.setName("test Update");
		
		//updating new station
		ResponseEntity<?> response = controller.update(insertedStation.getTrainStationId(), insertedStation, "fr");
		TrainStation updatedStation = (TrainStation) response.getBody();
		
		assertEquals("test Update", updatedStation.getName());
	}

	@Test
	public void testCheckBothStationError() {
		TrainStation station = new TrainStation("test", 5.0, 5.0);
		TrainStation station2 = new TrainStation("test2", 10.0, 10.0);

		ResponseEntity<?> newStation = controller.save(station, "fr");
		TrainStation insertedStation = (TrainStation) newStation.getBody();

		ResponseEntity<?> newStation2 = controller.save(station2, "fr");
		TrainStation insertedStation2 = (TrainStation) newStation2.getBody();

		Boolean response = controller.checkBothStationError(insertedStation.getTrainStationId(), insertedStation2.getTrainStationId(), "fr");
		assertTrue(response);


	}

}
