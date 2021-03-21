package com.spring.crud.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spring.crud.demo.model.Journey;
import com.spring.crud.demo.repository.JourneyRepository;
import com.spring.crud.demo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private JourneyRepository repository;

    @PersistenceContext
    private EntityManager entityManger;

    @Override
    public List<Journey> getAll() {
        return repository.findAll();
    }


    @Override
    public Journey save(Journey journey) {
        return repository.save(journey);
    }


}
