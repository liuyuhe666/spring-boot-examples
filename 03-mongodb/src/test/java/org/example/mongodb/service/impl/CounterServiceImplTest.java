package org.example.mongodb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.mongodb.SpringBootMongoDbApplicationTests;
import org.example.mongodb.model.Counter;
import org.example.mongodb.service.CounterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CounterServiceImplTest extends SpringBootMongoDbApplicationTests {
    @Autowired
    private CounterService counterService;

    @Test
    public void testCreateCounter() {
        Counter counter = new Counter();
        counter.setName("test");
        counter.setNum(0);
        counterService.createCounter(counter);
    }

    @Test
    public void testUpdateCounter() {
        List<Counter> result = counterService.getCounterByName("test");
        if (!result.isEmpty()) {
            Counter counter = result.get(0);
            counter.setNum(counter.getNum() + 1);
            counterService.updateCounter(counter);
            log.info("[testUpdateCounter] {}", counter);
        }
    }

    @Test
    public void testDeleteCounterById() {
        List<Counter> result = counterService.getCounterByName("test");
        if (!result.isEmpty()) {
            Counter counter = result.get(0);
            counterService.deleteCounterById(counter.getId());
            log.info("[testDeleteCounterById] {}", counter.getId());
        }
    }

    @Test
    public void testGetCounterByName() {
        List<Counter> result = counterService.getCounterByName("test");
        log.info("[testGetCounterByName] {}", result);
    }
}
