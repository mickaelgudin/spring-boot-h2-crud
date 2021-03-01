package com.spring.crud.demo.utils;

import java.util.Arrays;

import java.util.List;
import java.util.function.Supplier;
import com.spring.crud.demo.model.TrainStation;

public class HelperUtil {

    private HelperUtil() {
    }


    public static Supplier<List<TrainStation>> trainStationsSupplier = () ->
            Arrays.asList(
		            new TrainStation("Paris Montparnasse", 48.8402, 2.3193),
		            new TrainStation("Bordeaux St Jean", 44.8260022,-0.558805),
		            new TrainStation("Strasbourg", 48.5850571,7.7323068)
            );

}
