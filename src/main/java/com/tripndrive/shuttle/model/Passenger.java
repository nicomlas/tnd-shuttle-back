package com.tripndrive.shuttle.model;

import java.io.Serializable;

/**
 * Created by LG on 23/11/2015.
 */
public class Passenger implements Serializable{

    public enum State{
        waiting,
        picked
    }

    private String id;
    private String name;
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Passenger(String id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
