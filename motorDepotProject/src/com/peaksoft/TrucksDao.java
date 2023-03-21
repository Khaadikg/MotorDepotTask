package com.peaksoft;

import java.util.ArrayList;

public class TrucksDao {
    public static ArrayList<Truck> truckDB = new ArrayList<>();

    public static void addTruck(Truck truck) {
        truckDB.add(truck);
    }

    public static ArrayList<Truck> getAllDB() {
        return truckDB;
    }
}
