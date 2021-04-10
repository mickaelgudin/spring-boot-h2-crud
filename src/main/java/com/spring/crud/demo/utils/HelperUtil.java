package com.spring.crud.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.model.LineTrainStation;
import com.spring.crud.demo.model.TrainStation;

/**
 * Class for useful methods(get initial data, get formatted date)
 * 
 * @author mickaelgudin
 */
public class HelperUtil {
	DataFactory df = new DataFactory();

	public HelperUtil() {
	}

	/**
	 * get initial train stations
	 * 
	 * @return all stations to be inserted
	 */
	public List<TrainStation> getTrainsStations() {
		TrainStation versailles = df.getTrainStationVersaillesChantiers();
		TrainStation montparnasse = df.getTrainStationMontparnasse();
		TrainStation laDefense = df.getTrainStationLaDefense();

		LineTrainStation lineN = df.getLineWithGivenName("N");
		LineTrainStation lineU = df.getLineWithGivenName("U");

		// stations of line N
		df.setLineforTrainStation(lineN, versailles);
		df.setLineforTrainStation(lineN, montparnasse);

		// stations of line U
		df.setLineforTrainStation(lineU, versailles);
		df.setLineforTrainStation(lineU, laDefense);

		return Arrays.asList(versailles, montparnasse, laDefense);
	}

	/**
	 * journeys with increasing prices
	 * 
	 * @param depart
	 * @param arrivee
	 * @return
	 */
	public List<Journey> getJourneysWithIncreasingPrice(TrainStation depart, TrainStation arrivee,
			LineTrainStation line) {
		List<Journey> journeysWithIncreasingPrice = new ArrayList<>();

		for (int price = 10; price < 50; price = price + 10) {
			int day = price / 10; // setting prices for last 2-5 days

			journeysWithIncreasingPrice
					.add(df.getJourneyWithPrice(depart, arrivee, LocalDateTime.now().minusDays(day), price, line));
		}

		// we also want steady price at least for today
		journeysWithIncreasingPrice.addAll(getJourneysWithStablePrice(depart, arrivee, line));

		return journeysWithIncreasingPrice;
	}

	/**
	 * journeys with decreasing prices
	 * @param depart
	 * @param arrivee
	 * @return
	 */
	public List<Journey> getJourneysWithDecreasingPrice(TrainStation depart, TrainStation arrivee,
			LineTrainStation line) {
		List<Journey> journeysWithDecreasingPrice = new ArrayList<>();

		for (int price = 80; price > 10; price = price - 10) {
			int day = price / 10; // setting prices for last 2-5 days

			journeysWithDecreasingPrice
					.add(df.getJourneyWithPrice(depart, arrivee, LocalDateTime.now().minusDays(day), price, line));
		}

		// we also want steady price at least for today and tomorrow
		journeysWithDecreasingPrice.addAll(getJourneysWithStablePrice(depart, arrivee, line));

		return journeysWithDecreasingPrice;
	}

	/**
	 * journeys with stable prices
	 * 
	 * @param depart
	 * @param arrivee
	 * @param line
	 * @return journeys with stable prices
	 */
	public List<Journey> getJourneysWithStablePrice(TrainStation depart, TrainStation arrivee, LineTrainStation line) {
		List<Journey> journeysWithStablePrice = new ArrayList<>();

		LocalDateTime now = LocalDateTime.now();
		now = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), 0);
		// get schedule for today from 8am to 9pm
		for (int hour = 8; hour < 20; hour++) {
			Journey journeyForTommorow = df.getJourneyWithPrice(depart, arrivee, 
																now.plusDays(1).withHour(hour), 50, line);
			journeysWithStablePrice.add(journeyForTommorow);
			
			Journey newJourney = df.getJourneyWithPrice(depart, arrivee, now.withHour(hour), 50, line);
			journeysWithStablePrice.add(newJourney);
		}

		
		
		return journeysWithStablePrice;
	}

	/**
	 * format the given date with a certain pattern(for our dates stored in the
	 * database)
	 * @param date
	 * @return formatted date
	 */
	public static LocalDateTime getFormattedDate(LocalDateTime date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return LocalDateTime.parse(date.format(DateTimeFormatter.ofPattern(pattern)),
				DateTimeFormatter.ofPattern(pattern));
	}

}
