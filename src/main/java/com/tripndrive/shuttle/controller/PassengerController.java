package com.tripndrive.shuttle.controller;

import com.tripndrive.shuttle.model.Passenger;
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

    Map<Long, Passenger> passengerMap = new HashMap<Long, Passenger>();

    Long nextId = (long) 0;

    @PostConstruct
    public void init(){

        for(nextId = (long) 0; nextId<10; nextId++){
            passengerMap.put(nextId, new Passenger(nextId, "passenger " + nextId, Passenger.State.waiting ));
        }

    }


    @RequestMapping(method = RequestMethod.GET)
    public Collection<Passenger> queryPassenger(){
        return passengerMap.values();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Passenger create(@RequestBody Passenger passenger){

        if(passenger == null || passenger.getName() == null || passenger.getState() == null){
            return null;
        }

        passenger.setId(++nextId);
        passengerMap.put(passenger.getId(), passenger);

        return passenger;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Passenger update(@PathVariable Long id, @RequestParam(value = "state") Passenger.State state){

        Passenger p = passengerMap.get(id);

        if(p == null){
            return null ;
        }

        p.setState(state);

        passengerMap.put(id, p);

        return p;
    }

}
