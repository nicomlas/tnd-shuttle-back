package com.tripndrive.shuttle.controller;

import com.tripndrive.shuttle.exception.ThisIsBadException;
import com.tripndrive.shuttle.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by LG on 23/11/2015.
 */
@RestController
@RequestMapping(value = "/passengers")
public class PassengerController {

    @Autowired
    private RedisTemplate redisTemplate;

    Map<String, Passenger> passengerMap = new HashMap<>();

    Long nextId = (long) 0;

    @PostConstruct
    public void init(){

        for(nextId = (long) 0; nextId <10; nextId++){
            Passenger p = store(new Passenger(null, "passenger " + nextId , Passenger.State.waiting ));
            passengerMap.put(p.getId(), p);
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Passenger> queryPassenger(){
        return passengerMap.values();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Passenger> create(@RequestBody Passenger passenger) throws ThisIsBadException {

        if(passenger == null || passenger.getName() == null || passenger.getState() == null || passengerMap.get(passenger.getId()) != null){
            throw new ThisIsBadException();
        }

        passenger = store(passenger);

        return new ResponseEntity(passenger, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Passenger update(@PathVariable Long id, @RequestParam(value = "state") Passenger.State state) throws ThisIsBadException {

        Passenger p = retrieve(id);

        if(p == null){
            throw new ThisIsBadException();
        }

        p.setState(state);

        p = store(p);
        passengerMap.put(p.getId(), p);

        return p;
    }

    private Passenger store(Passenger passenger){

        String key = getKey(passenger);

        passenger.setId(key);

        redisTemplate.opsForHash().put(key, key, passenger);

        return passenger;
    }

    private Passenger retrieve(Long passengerId){

        // Retrieving the User object from the Redis by using the suggested key
        return (Passenger) redisTemplate.opsForHash().get(passengerId, passengerId);

    }

    private List<Passenger> retrieveAll(){
       //TODO
//        redisTemplate.keys("passenger-");
        return null;
    }

    private String getKey(Passenger passenger){

        if(passenger.getId() != null) return "passenger-"+ (passenger.getId());

        return "passenger-"+ (++nextId);

    }

}
