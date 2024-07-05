package org.example.mongodb.service.impl;

import org.example.mongodb.model.Counter;
import org.example.mongodb.repository.CounterRepository;
import org.example.mongodb.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createCounter(Counter counter) {
        counterRepository.save(counter);
    }

    @Override
    public void updateCounter(Counter counter) {
        counterRepository.save(counter);
    }

    @Override
    public void deleteCounterById(String id) {
        counterRepository.deleteById(id);
    }

    @Override
    public List<Counter> getCounterByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Counter.class);
    }
}
