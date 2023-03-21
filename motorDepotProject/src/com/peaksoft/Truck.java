package com.peaksoft;

import static com.peaksoft.Status.*;

public class Truck {
    private int id;
    private static int counter = 1;
    private String name;
    private String driver;
    private Status state;

    public Truck(String name, String driver, Status state) {
        this.id = counter++;
        this.name = name;
        this.driver = driver;
        this.state = state;
    }

    //    public static Truck truckCreate(String name, String driver, Status state) {
//        Truck truck = new Truck();
//        truck.id = counter++;
//        truck.name = name;
//        truck.driver = driver;
//        truck.state = state;
//        return truck;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }
}
