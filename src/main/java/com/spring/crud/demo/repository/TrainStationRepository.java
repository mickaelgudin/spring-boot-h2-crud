package com.spring.crud.demo.repository;

import com.spring.crud.demo.model.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for train station
 * @author MGud
 */
public interface TrainStationRepository extends JpaRepository<TrainStation, Integer> {

}
