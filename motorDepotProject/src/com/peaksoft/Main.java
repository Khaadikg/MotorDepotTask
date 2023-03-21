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
    public static String URL = "./gson.truck";
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
        //Приоброзование Json файла в ArrayList обьектов джава
        ArrayList<Truck> trucks = GSON.fromJson(read(), new TypeToken<ArrayList<Truck>>(){}.getType());

        System.out.println("#  | Bus    |  Driver  |  State\n\n"
                         + "———+————————+——————————+—————————");
        for (Truck truck : trucks) {
            System.out.printf("%d   |%s   |%s   |%s \n", truck.getId(), truck.getName(), truck.getDriver(), truck.getState());
        }
    }



    public static String read() {
        try {
            FileReader reader = new FileReader(URL);
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
    public static void write(String truck) {
        Path path = Paths.get(URL);
        try {
            Files.writeString(path, truck, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}