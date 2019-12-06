package rental.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class WestminsterVehicleManager implements  RentalVehicleManager{

     private static final int  PARKINGSPACE = 50;
    private int spaceAllReadyOccupied = 0;
    private ArrayList<Vehicle> addedVehicles = new ArrayList<>();
    public WestminsterVehicleManager() {
    }


    // adding the vehicle to the array if there is space in the parking lot
    @Override
    public void addNewVehicle(Vehicle vehicle) {
        try {

            long fi = Shared.connection().count();
            spaceAllReadyOccupied = (int) fi;

        }catch (Exception e){
            System.out.println(e);
        }
        //checking whether database has more than 50 records
        if (PARKINGSPACE > (spaceAllReadyOccupied + addedVehicles.size())) {
            addedVehicles.add(vehicle);
        }else{
            System.out.println("parking lot is full");
        }
    }
    //deleting the vehicle by the plate number
    @Override
    public void deleteVehicle(int plateNumber) {
        try {

            BasicDBObject del = new BasicDBObject("plateNumber",plateNumber);
            FindIterable<Document> fi = Shared.connection().find(del).limit(1);
            MongoCursor<Document> records = fi.iterator();

            Document doc = records.next();
            String type = doc.getString("type");
            String make = doc.getString("make");
            String model = doc.getString("model");

            Shared.connection().deleteOne(del);
            int counter =(int) Shared.connection().count();
            int availableSpace = 50 - counter;

            System.out.println("Type: " + type + "\nMake: " + make + "\nmodel: " + model + "\navailable Paking spaces: " + availableSpace);



        }catch (NoSuchElementException e){
            System.out.println("The plate number you Enter does not exist");
        }
    }
    //printing the plate number and the type  which is sorted by make
    @Override
    public void printVehicle() {
        try {

            FindIterable<Document> fi = Shared.connection().find().sort(new BasicDBObject("make",1));
            MongoCursor<Document> iterator = fi.iterator();
            //while the iterator has a record print the plate number and the type
            while (iterator.hasNext()) {
                Document doc = iterator.next();
                int plateNumber = doc.getInteger("plateNumber");
                String type = doc.getString("type");
                String make = doc.getString("make");
                System.out.println("\nplate number: " + plateNumber + "\t type: " + type + "\tmake: " + make);
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }
    //saving the vehicles which are in the vehicle array
    @Override
    public void saveVehicle() {

        for (Vehicle vehicle: addedVehicles) {
            //getting the class of the vehicle and putting to the String data type, type variables
            String type = vehicle.getClass().toString();
            //if the class is a car then execute this code block
            if ("class rental.manager.Car".equals(type)){
                //type casting vehicle from Vehicle to Car
                Car car = (Car) vehicle;
                try {
                    //inserting the car to the database
                    Shared.connection().insertOne(
                            new Document()
                                    .append("plateNumber",car.getPlateNumber())
                                    .append("make",car.getMake())
                                    .append("model",car.getModel())
                                    .append("tripMeter",car.getTripMeter())
                                    .append("autoTransmission",car.getAutoTrasnmission())
                                    .append("available",car.getAvailable())
                                    .append("rentFee",car.getRentFee())
                                    .append("engineCapacity",car.getEngineCapacity())
                                    .append("fuelType",car.getFuelType())
                                    .append("type","car")
                                    .append("numOfSeats",car.getNumOfSeat())
                                    .append("numOfDoors",car.getNumOfDoors())
                                    .append("airCondition",car.getAirCondition())
                                    .append("bodyType",car.getBodyType())

                    );
                    System.out.println("insertion done");

                }catch (Exception e){
                    System.out.println(e);
                }
            }else if ("class rental.manager.MotorBike".equals(type)){//if the class is motorbike then execute this code block
                MotorBike motorBike = (MotorBike) vehicle;
                try {
                    Shared.connection().insertOne(
                            new Document()
                                    .append("plateNumber",motorBike.getPlateNumber())
                                    .append("make",motorBike.getMake())
                                    .append("model",motorBike.getModel())
                                    .append("tripMeter",motorBike.getTripMeter())
                                    .append("autoTransmission",motorBike.getAutoTrasnmission())
                                    .append("available",motorBike.getAvailable())
                                    .append("rentFee",motorBike.getRentFee())
                                    .append("engineCapacity",motorBike.getEngineCapacity())
                                    .append("fuelType",motorBike.getFuelType())
                                    .append("type","motorbike")
                                    .append("storageSpace",motorBike.getStorageSpace())
                                    .append("sideCar",motorBike.getSideCar())

                    );
                    System.out.println("insertion done");

                }catch (Exception e){
                    System.out.println(e);
                }
            }else {
                System.out.println("nothing done");
            }
        }

    }
    //returning the array added vehicle
    public ArrayList<Vehicle> returnArray(){
        return addedVehicles;
    }
    //generating the report using file writing
    @Override
    public void generateReport() {
        try {
            FindIterable<Document> fi = Shared.connection().find().sort(new BasicDBObject("type",1));//getting all the records from the database
            MongoCursor<Document> records = fi.iterator();//iterating thorough the records and putting it to a records which is data type MongoCursor
            //while records has a record loop through the code
            while (records.hasNext()) {
                Document doc = records.next();
                int plateNumber = doc.getInteger("plateNumber");
                String make = doc.getString("make");
                String model = doc.getString("model");
                int tripMeter = doc.getInteger("tripMeter");
                boolean autoTransmission = doc.getBoolean("autoTransmission");
                boolean available = doc.getBoolean("available");
                int rentFee = doc.getInteger("rentFee");
                int engineCap = doc.getInteger("engineCapacity");
                String fuelType = doc.getString("fuelType");
                String type = doc.getString("type");
                int numOfSeats = 0;
                int numOfDoors = 0;
                boolean airCondition = false;
                String bodyType = null;
                boolean storageSpace = false;
                boolean sideCar = false;
                if (type.equals("car")){
                    numOfSeats = doc.getInteger("numOfSeats");
                    numOfDoors = doc.getInteger("numOfDoors");
                    airCondition = doc.getBoolean("airCondition");
                    bodyType = doc.getString("bodyType");
                }
                else if (type.equals("bodyType")){
                    storageSpace = doc.getBoolean("storageSpace");
                    sideCar = doc.getBoolean("sideCar");
                }
                //creating a FileWriting object with the file name vehicle_report.txt and append true
                FileWriter fw = new FileWriter("vehicle_report.txt",true);
//                creating the PrintWriter passing the fileWriter as the parameter
                PrintWriter pw = new PrintWriter(fw);

                pw.println(
                        "\nType: " + type
                        + ", Plate Number: " + plateNumber
                        + ", make: " + make
                        + ", model: " + model
                        + ", tripMeter: " + tripMeter
                        + ", is auto transmission: " + autoTransmission
                        + ", available: " + available
                        + ", rent Fee: " + rentFee
                        + ", engine Capacity: " + engineCap
                        + ", fuelType: " + fuelType
                        + ", numOfSeats: " + numOfSeats
                        + ", numOfDoors: " + numOfDoors
                        + ", airCondition: " + airCondition
                        + ", bodyType: " + bodyType
                        + ", storageSpace: " + storageSpace
                        + ", sideCar: " + sideCar
                );
                //sending pw content to the file
                pw.flush();
                //closing the pw
                pw.close();

            }


        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void openGUI(){
        //from this it will open the specified url from the default browser in the desktop
        Desktop dt = java.awt.Desktop.getDesktop();
        try {

            URI url = new URI("http://localhost:4200/");
            dt.browse(url);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
