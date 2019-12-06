package rental.manager;

public abstract class Vehicle {

    private int plateNumber;
    private String make;
    private String model;
    private int tripMeter;
    private boolean autoTrasnmission;
    private boolean available;
    private int rentFee;
    private int engineCapacity;
    private String fuelType;

    protected void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    protected void setMake(String make) {
        this.make = make;
    }

    protected void setModel(String model) {
        this.model = model;
    }

    protected void setInitialMillage(int tripMeter) {
        this.tripMeter = tripMeter;
    }


    protected void setAutoTrasnmission(boolean autoTrasnmission) {
        this.autoTrasnmission = autoTrasnmission;
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    protected void setRentFee(int rentFee) {
        this.rentFee = rentFee;
    }

    protected void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    protected void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getTripMeter() {
        return tripMeter;
    }


    public boolean getAutoTrasnmission() {
        return autoTrasnmission;
    }

    public boolean getAvailable() {
        return available;
    }

    public int getRentFee() {
        return rentFee;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }
}
