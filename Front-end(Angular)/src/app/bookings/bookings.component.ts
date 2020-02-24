import {Component, Input, OnInit} from '@angular/core';
import {VehiclesComponent} from "../vehicles/vehicles.component";
import {APIService} from "../share/api.service";
import {Schedule} from "../models/schedule";

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {
  private scheduled: Document[];
  private  userPickUp: String;
  private  userDropOff: String;
  private userFirstName:String;
  private userLastName:String;
  private userContactNumber:String;
  private userEmail:String;
  private dbPickUp: String;
  private isBooked:boolean = false;
  private appearForm:boolean = false;
  private dbDropOff: String;
  private error:String;
  private schedule: Schedule;

  constructor(private vehicle:VehiclesComponent, private apiService: APIService) { }

  ngOnInit() {
  }


  public vehicleBooking(){
    this.schedule = new Schedule(this.vehicle.selectedVehicleDetails["plateNumber"],this.userPickUp,this.userDropOff,this.userFirstName,this.userLastName,this.userContactNumber,this.userEmail,new Date().getTime().toString());


    //subscribing to the booking method in the service
    this.apiService.booking(this.schedule).subscribe(
      res => {
        console.log("put successful",res)
        alert("Your Booking has been placed")
        location.reload();

      },
      error => {
        console.error("the req not sent");
        this.error = error;
        alert("the req not send");
      }

    );
  }

  //checking whether the the date is available or not
  public checkBooking() {
    this.scheduled = this.vehicle.selectedVehicleDetails["schedule"];
    //if the vehicle has never been booked then make the form appear
    if (this.schedule == null){
      this.appearForm =true;
    }
    //looping through every booking(schedule)
    for (let i=0;i<this.scheduled.length;i++){
      this.dbPickUp = this.scheduled[i]["pickup"];
      this.dbPickUp = this.dbPickUp.replace('T00:00:00.000+0000','');
      this.dbDropOff = this.scheduled[i]["dropoff"];
      this.dbDropOff = this.dbDropOff.replace('T00:00:00.000+0000','');

      this.isBooked = false;
      this.appearForm = false;
      //if the booking pickup date is in between a booked date isBooked become true
      if(this.checkDate(this.dbPickUp,this.dbDropOff,this.userPickUp)){
        this.isBooked = true;
        break;
      }
      //if the booking dropooff date is in between a booked date isBooked become true
      else if (this.checkDate(this.dbPickUp,this.dbDropOff,this.userDropOff)) {
        this.isBooked = true;
        break;
      }else { //if there are no booking appear the form to book the vehicle
        this.appearForm = true;
      }



    }


  }
  //checking whether the  checking date is in between a already booked pickup date and dropoff date
  public checkDate(pickup,dropoff,checking) {

    let pickupDate:Number,dropoffDate: Number,checkingDate:Number;
    pickupDate = Date.parse(pickup);
    dropoffDate = Date.parse(dropoff);
    checkingDate = Date.parse(checking);

    if((checkingDate <= dropoffDate && checkingDate >= pickupDate)) {
      return true;
    }
    return false;
  }
}
