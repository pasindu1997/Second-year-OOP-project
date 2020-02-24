package pasindu.coursework;

import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

//this is the controller class where request will first land in
@RestController
public class Controller {
    DatabaseSeeder db = new DatabaseSeeder();

    @RequestMapping("api/allvehicles")
    @CrossOrigin("http://localhost:4200") // only this url can access the recourse in this api
    public ArrayList<Document> allVehicles(){
        return db.getAllRecord();
    }


    @RequestMapping(method = RequestMethod.PUT, value = "api/booking/{plateNumber}")
    @CrossOrigin("http://localhost:4200") // only this url can access the recourse in this api
    //getting the body in json as Schedule and getting the plate number from the url.
    public void putBooking2(@RequestBody Schedule schedule, @PathVariable int plateNumber){
        Date pickup = schedule.getPickupDate();
        Date dropoff = schedule.getDropoffDate();
        String firstName = schedule.getFirstName();
        String lastName = schedule.getLastName();
        String contactNumber = schedule.getContactNumber();
        String email = schedule.getEmail();
        Date date = schedule.getBookedDateTime();

        //accessing the method in database seeder in order to update the schedule
        db.updateSchedule(plateNumber,pickup,dropoff,firstName,lastName,contactNumber,email,date);
    }


}
