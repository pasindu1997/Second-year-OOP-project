package rental;


import rental.manager.*;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        boolean res;

        WestminsterVehicleManager vehicleManager = new WestminsterVehicleManager();
        do {
            Scanner sc = new Scanner(System.in);
            //asking the user for the operation
            System.out.println("Select a operation:\n " +
                    "Press 1 to Add New Vehicle\n" +
                    " Press 2 to Delete vehicle\n" +
                    " Press 3 to Print the vehicle list\n" +
                    " Press 4 to generate report \n" +
                    " Press 5 to open GUI");
            //input validation for a integer withing a range of 1 and 5
            int input = Shared.getNumberInput(sc, 1, 5);
            //switch to operate the relevant case
            switch (input) {
                case 1:

                    boolean addAgain;
                    do {
                        //asking to add a vehicle or not
                        System.out.println("Press 1 to enter car\n" +
                                "press 2 to enter Motor bike");
                        int type = Shared.getNumberInput(sc,1,2);
                        //if to add a car ask these cars
                        if (type == 1) {



                                System.out.println("Enter the plate number(Integer)");
                                int plateNumber = Shared.getNumber(sc);

                                System.out.println("Enter the Make(String) ");
                                String make = sc.next();

                                System.out.println("Enter the model(String): ");
                                String model = sc.next();

                                System.out.println("Enter the Trip meter reading(Integer): ");
                                int meterReading = Shared.getNumber(sc);

                                System.out.println("Is it auto transmission (y/n): ");
                                Boolean autoTransmission = Shared.getBoolean(sc);

                                System.out.println("Is it available (y/n): ");
                                Boolean available = Shared.getBoolean(sc);

                                System.out.println("Enter the rent fee per KM (Integer): ");
                                int rentFee = Shared.getNumber(sc);

                                System.out.println("Enter the Engine capacity(Integer): ");
                                int engCap = Shared.getNumber(sc);

                                System.out.println("Enter the Fuel Type (String): ");
                                String fuelType = sc.next();

                                System.out.println("Enter the Number of Seats(Integer): ");
                                int noOfSeats = Shared.getNumber(sc);

                                System.out.println("Enter the Number of Doors(Integer): ");
                                int noOfDoors = Shared.getNumber(sc);

                                System.out.println("Is it air conditioned(y/n): ");
                                Boolean airCondition = Shared.getBoolean(sc);

                                System.out.println("Enter the body Type(String): ");
                                String bodyType = sc.next();
                                // making a object of Vehicle type with the Car constructor
                                Vehicle car = new Car(noOfSeats, noOfDoors, airCondition, bodyType, meterReading, autoTransmission, available, rentFee, engCap, fuelType, model, make, plateNumber);
                                //adding the vehicle to the vehicle array
                                vehicleManager.addNewVehicle(car);



                        }
                        //if type is motor bike the executing this code block
                        if (type == 2) {
                            try {
                                System.out.println("Enter the plate number(Integer)");
                                int plateNumber = Shared.getNumber(sc);

                                System.out.println("Enter the Make(String) ");
                                String make = sc.next();

                                System.out.println("Enter the model(String): ");
                                String model = sc.next();

                                System.out.println("Enter the Trip meter reading(Integer): ");
                                int meterReading = Shared.getNumber(sc);

                                System.out.println("Is it auto transmission (y/n): ");
                                Boolean autoTransmission = Shared.getBoolean(sc);

                                System.out.println("Is it available (y/n): ");
                                Boolean available = Shared.getBoolean(sc);

                                System.out.println("Enter the rent fee per KM (integer): ");
                                int rentFee = Shared.getNumber(sc);

                                System.out.println("Enter the Engine capacity(integer): ");
                                int engCap = Shared.getNumber(sc);

                                System.out.println("Enter the Fuel Type (y/n): ");
                                String fuelType = sc.next();

                                System.out.println("is there a storage space (y/n) ");
                                boolean storageSpace = Shared.getBoolean(sc);

                                System.out.println("is there is a Side car(y/n): ");
                                boolean sideCar = Shared.getBoolean(sc);

                                //making a Vehicle object with the MotorBike constructor
                                Vehicle motorBike = new MotorBike(storageSpace, sideCar, meterReading, autoTransmission, available, rentFee, engCap, fuelType, model, make, plateNumber);
                                //adding the vehicle to the array
                                vehicleManager.addNewVehicle(motorBike);
                            } catch (Exception e) {
                                System.out.println("enter Correct values");

                            }

                        }
                        //prompting whether to add another vehicle
                        System.out.println("press y to add anther vehicle or press n to save the vehicles to the database");
                        addAgain = Shared.getBoolean(sc);
                        //if y then loop to add a new vehicle
                    }while (addAgain);
                    //if user says n which means user has stopped entering vehicles then save all the vehicles in the array to the database
                    if (!addAgain){
                        //accessing the saveVehicle() in the vehicleManager
                        vehicleManager.saveVehicle();
                        //clearing the array of addedVehicles
                        vehicleManager.returnArray().clear();
                        break;
                    }
                case 2:
                    //prompt for the plate number
                    System.out.println("Enter the number plate");
                    int plateNo = Shared.getNumber(sc);
                    //accessing the deleteVehicle method in vehicleManager
                    vehicleManager.deleteVehicle(plateNo);
                    break;

                case 3:
                    //invoking the printVehicle method in vehicleManager
                    vehicleManager.printVehicle();
                    break;
                case 4:
                    //invoking the generateReport method in vehicleManager
                    vehicleManager.generateReport();
                    break;
                case 5:
                    vehicleManager.openGUI();
            }
            //asking the user whether to loop the whole process from the begining
            System.out.println("do you want to repeat process again?(y/n)");
            res = Shared.getBoolean(sc);
        }while (res);//loop again if the user wants to
        //if user wants to leave then show the below message
        System.out.println("Bye, Have a nice day");
    }


}
