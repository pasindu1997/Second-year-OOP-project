package rental.manager;

public class MotorBike extends Vehicle {

    private boolean storageSpace;
    private boolean sideCar;

    public MotorBike(boolean storageSpace, boolean sideCar, int initialMilage, boolean autoTransmission, boolean available, int rentFee, int engineCapacity,
                     String fuelType, String model, String make, int plateNumber) {
        super.setInitialMillage(initialMilage);
        super.setAutoTrasnmission(autoTransmission);
        super.setAvailable(available);
        super.setRentFee(rentFee);
        super.setEngineCapacity(engineCapacity);
        super.setFuelType(fuelType);
        super.setModel(model);
        super.setMake(make);
        super.setPlateNumber(plateNumber);
        this.storageSpace = storageSpace;
        this.sideCar = sideCar;
    }

    public boolean getStorageSpace() {
        return storageSpace;
    }

    public boolean getSideCar() {
        return sideCar;
    }


}
