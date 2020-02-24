package pasindu.coursework;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@EntityScan
public class Schedule {
    //getting the string and shaping it to json format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date pickupDate;
    //getting the string and shaping it to json format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dropoffDate;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date bookedDateTime;

    //constructor
    public Schedule(Date pickupDate, Date dropoffDate, String firstName, String lastName, String contactNumber, String email,Date bookedDateTime) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.bookedDateTime = bookedDateTime;
    }
    //only getters because i only want to access this variables
    public Date getBookedDateTime() {
        return bookedDateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getPickupDate() {
        return pickupDate;
    }


    public Date getDropoffDate() {
        return dropoffDate;
    }
}

