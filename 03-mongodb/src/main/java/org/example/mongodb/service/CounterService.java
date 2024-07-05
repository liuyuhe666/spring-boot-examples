package org.example.mongodb.service;

import org.example.mongodb.model.Counter;

import java.util.List;

public interface CounterService {
    void createCounter(Counter counter);

    void updateCounter(Counter counter);

    void deleteCounterById(String id);

    List<Counter> getCounterByName(String name);
}
