package com.spring.crud.demo.repository;

import com.spring.crud.demo.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JourneyRepository extends JpaRepository<Journey, Integer> {



}