package com.tripndrive.shuttle.model;

/**
 * Created by LG on 23/11/2015.
 */
public class Passenger {

    public enum State{
        waiting,
        picked
    }

    private Long id;
    private String name;
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Passenger(Long id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
