package rental.manager;

public class Car extends Vehicle{

    private int numOfSeat;
    private int numOfDoors;
    private boolean airCondition;
    private String bodyType;

    public Car(int numOfSeat, int numOfDoors, boolean airCondition, String bodyType, int tripMeter, boolean autoTransmission, boolean available, int rentFee, int engineCapacity,
               String fuelType, String model, String make, int plateNumber) {
        super.setInitialMillage(tripMeter);
        super.setAutoTrasnmission(autoTransmission);
        super.setAvailable(available);
        super.setRentFee(rentFee);
        super.setEngineCapacity(engineCapacity);
        super.setFuelType(fuelType);
        super.setModel(model);
        super.setMake(make);
        super.setPlateNumber(plateNumber);
        this.numOfSeat = numOfSeat;
        this.numOfDoors = numOfDoors;
        this.airCondition = airCondition;
        this.bodyType = bodyType;

    }



    public int getNumOfSeat() {
        return numOfSeat;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public boolean getAirCondition() {
        return airCondition;
    }

    public String getBodyType() {
        return bodyType;
    }
}
