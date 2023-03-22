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
import java.util.Random;
import java.util.Scanner;

public class TruckService {
    public static GsonBuilder GSON_BUILDER = new GsonBuilder();
    public static Gson GSON = GSON_BUILDER.create();
    public static String TruckURL = "./gson.truck";
    public static String DriverURL = "./gson.driver";

// --- Коменченый Код ниже используется только для создания "gson" файла

    /*
            TrucksDao.addTruck(new Truck("Mercedes" , "Asan", BASE));
            TrucksDao.addTruck(new Truck("Volvo" , "Uson", BASE));
            TrucksDao.addTruck(new Truck("Renault" , "Aziz", BASE));
            String gson = GSON.toJson(TrucksDao.getAllDB());
            System.out.println(gson);
            write(gson);
    */
    static ArrayList<Driver> drivers = getArrayListOfDrivers(DriverURL);
    static ArrayList<Truck> trucks = getArrayListOfTrucks(TruckURL);

    // ----- 3 part -------
    public static void changeDriver(String driver) throws MyException {
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getState() == Status.BASE) {
                trucks.get(i).setDriver(driver);
                break;
            }
            else throw new MyException("There is no free trucks!");
        }
    }

    public static void startDriving(Truck truck) throws MyException {
        if (truck.getState() == Status.BASE) {
            if (truck.getDriver() != null) {
                truck.setState(Status.ROAD);
                System.out.println(truck.getDriver() + " - успешно вышли на маршрут");
            } else {
                System.out.println(truck.getName() + " - Уже в уже есть водиетль! - " + truck.getDriver());
                System.out.println(truck.getDriver() + " - успешно вышли на маршрут");
            }
        }
        else if (truck.getState() == Status.REPAIR) {
            Random ran = new Random();
            if (ran.nextInt(1, 2) == 1){
                truck.setState(Status.BASE);
            }
            else truck.setState(Status.ROAD);
        }
        else throw new MyException("Truck is not ready or On the Road!");
    }
    public static void startRepair(Truck truck) throws MyException{
        if (truck.getState() != Status.REPAIR) {
            truck.setState(Status.REPAIR);
        }
        else throw new MyException("Уже в ромонте!");
    }

    public static void start() {
        System.out.println("#  | Driver    |  Bus  \n"
                + "———+———————————+——————————");
        for (Driver driver:  drivers) {
            System.out.printf("%d   |%s   |\n", driver.getId(), driver.getName());
        }

        System.out.println("\n\n");

        System.out.println("#  | Bus    |  Driver  |  State\n"
                         + "———+————————+——————————+—————————");
        for (Truck truck : trucks) {
            System.out.printf("%d   |%s   |%s   |%s \n", truck.getId(), truck.getName(), truck.getDriver(), truck.getState());
        }

        System.out.println("\n");
        System.out.println("Введите ID грузовика!");
        Scanner scanner = new Scanner(System.in);
         while (true) {
             int id = Integer.parseInt(scanner.nextLine());
             id -= 1;
             try {
                 System.out.printf("N : %d\nBUS: %s\nDriver : \nTruck State: %s", +trucks.get(id).getId(), trucks.get(id).getName(),
                         trucks.get(id).getDriver(), trucks.get(id).getState());
                 System.out.println("\n");
             } catch (IndexOutOfBoundsException e) {
                 e.printStackTrace();
             }
         }
        //--- код ниже спользуется только для создания gson файла Водителей
//        for (Truck truck: trucks) {
//            DriverDao.addToDriversDB(new Driver(truck.getId(), truck.getDriver()));
//        }
    }
//        String gson = GSON.toJson(DriverDao.drivers);
//        write(DriverURL, gson);


        public static ArrayList getArrayListOfTrucks (String url){
            //Приоброзование Json файла в ArrayList обьектов джава
            //save our new arraylist into DB
            TrucksDao.truckDB = GSON.fromJson(read(url), new TypeToken<ArrayList<Truck>>() {
            }.getType());
            return GSON.fromJson(read(url), new TypeToken<ArrayList<Truck>>() {
            }.getType());
        }
        public static ArrayList getArrayListOfDrivers (String url){
            //Приоброзование Json файла в ArrayList обьектов джава
            DriverDao.drivers = GSON.fromJson(read(url), new TypeToken<ArrayList<Truck>>() {
            }.getType());
            return GSON.fromJson(read(url), new TypeToken<ArrayList<Driver>>() {
            }.getType());
        }
        public static String read (String url){
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
        public static void write (String url, String object){
            Path path = Paths.get(url);
            try {
                Files.writeString(path, object, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
