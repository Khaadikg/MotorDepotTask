package com.peaksoft;

import java.util.ArrayList;

public class DriverDao {
    static ArrayList<Driver> drivers = new ArrayList<>();
    public static void addToDriversDB(Driver driver) {
        drivers.add(driver);
    }
}
