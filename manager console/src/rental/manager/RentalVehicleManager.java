package rental.manager;

public interface RentalVehicleManager {

    void addNewVehicle(Vehicle vehicle);
    void deleteVehicle(int plateNumber);
    void printVehicle();
    void saveVehicle();
    void generateReport();
}
