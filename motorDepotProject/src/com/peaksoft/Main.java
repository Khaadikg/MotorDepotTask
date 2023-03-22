package com.peaksoft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import static com.peaksoft.Status.*;


public class Main {
    public static GsonBuilder GSON_BUILDER = new GsonBuilder();
    public static Gson GSON = GSON_BUILDER.create();
    public static String TruckURL = "./gson.truck";
    public static String DriverURL = "./gson.driver";
    public static void main(String[] args) {

// --- Коменченый Код ниже используется только для создания "gson" файла

/*
        TrucksDao.addTruck(new Truck("Mercedes" , "Asan", BASE));
        TrucksDao.addTruck(new Truck("Volvo" , "Uson", BASE));
        TrucksDao.addTruck(new Truck("Renault" , "Aziz", BASE));
        String gson = GSON.toJson(TrucksDao.getAllDB());
        System.out.println(gson);
        write(gson);
*/
        ArrayList<Driver>  drivers = getArrayListOfDrivers(DriverURL);
        System.out.println("#  | Driver    |  Bus  \n\n"
                         + "———+———————————+——————————");
        for (Driver driver:  drivers) {
            System.out.printf("%d   |%s   |\n", driver.getId(), driver.getName());
        }

//        System.out.println("#  | Bus    |  Driver  |  State\n\n"
//                         + "———+————————+——————————+—————————");
//        for (Truck truck : trucks) {
//            System.out.printf("%d   |%s   |%s   |%s \n", truck.getId(), truck.getName(), truck.getDriver(), truck.getState());
//        }

        //--- код ниже спользуется только для создания gson файла Водителей
//        for (Truck truck: trucks) {
//            DriverDao.addToDriversDB(new Driver(truck.getId(), truck.getDriver()));
//        }
//        String gson = GSON.toJson(DriverDao.drivers);
//        write(DriverURL, gson);

    }



    public static ArrayList getArrayListOfTrucks(String url) {
        //Приоброзование Json файла в ArrayList обьектов джава
        return GSON.fromJson(read(url), new TypeToken<ArrayList<Truck>>(){}.getType());
    }
    public static ArrayList getArrayListOfDrivers(String url) {
        //Приоброзование Json файла в ArrayList обьектов джава
        return GSON.fromJson(read(url), new TypeToken<ArrayList<Driver>>(){}.getType());
    }
    public static String read(String url) {
        try {
            FileReader reader = new FileReader(url);
            Scanner scan = new Scanner(reader);

            while (scan.hasNext()) {
                return scan.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Использовать только при пресоздании gson файла!
    public static void write(String url, String object) {
        Path path = Paths.get(url);
        try {
            Files.writeString(path, object, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}