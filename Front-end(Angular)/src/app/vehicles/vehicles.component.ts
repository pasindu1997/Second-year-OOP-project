import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {APIService} from "../share/api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesComponent implements OnInit {
  vehicles: Document[] = [];
  selectedVehicleDetails: Document;
  available: String;
  Transmission: String;
  aircondition: String;
  searchString: String;
  //initializing the APIService
  constructor(private apiService: APIService) { }

  //this method is called at the very first of the component life cycle
  ngOnInit() {
    this.getAllVehicles();
  }
  //from this method it catches the responds and putting it to the vehicle array which is a Document array
  public getAllVehicles(){
    this.apiService.getAllVehicles().subscribe(
      res => {
        this.vehicles = res;
      },
      error => {
        alert("Error, some thing has happened")
      }
    );
  }
  //from this i get the details of the selected vehicles and putting it to a Document variable
  selectedVehicle(vehicle: Document) {
    this.selectedVehicleDetails = vehicle;

  }
  //from this i am checking the whether the selected vehicle is available or not
  availability() {
    if (this.selectedVehicleDetails["available"]){
      this.available = "Yes"
    }else {
      this.available = "No"
    }
  }
  //checking whether the date is booked or not
  isSelected(){
    return !this.selectedVehicleDetails;
  }
  //checking whether the vehicle is auto or manual
  transmission() {
    if (this.selectedVehicleDetails["autoTransmission"]){
      this.Transmission = "Auto"
    }else {
      this.Transmission = "Manual"
    }
  }
  //checking whether the vehicle is air condition or not
  isAirConditioned() {
    if (this.selectedVehicleDetails["type"] == "car"){

      if (this.selectedVehicleDetails["airCondition"]){
        this.aircondition = "Yes"
      }else {
        this.aircondition = "No"
      }
    }else {
      this.aircondition = "Null"
    }
  }
  //getting the number of doors of the selected vehicle
  numberOFDoors():any {
    if (this.selectedVehicleDetails["type"] == "car"){
      return this.selectedVehicleDetails["numOfDoors"];
    }else {
      return "Null";
    }
  }
  //getting the number of seats of the selected vehicle
  numberOFSeat():any {
    if (this.selectedVehicleDetails["type"] == "car"){
      return this.selectedVehicleDetails["numOfSeats"];
    }else {
      return "Null";
    }
  }
  //return the name of the body type of the selected vehicle
  bodyType():String {
    if (this.selectedVehicleDetails["type"] == "car"){
      return this.selectedVehicleDetails["bodyType"];
    }else {
      return "Null";
    }
  }
  //checking whether the storage space of the selected vehicle is available or not
  storageSpace():String {
    if (this.selectedVehicleDetails["type"] == "motorbike"){
      if (this.selectedVehicleDetails["storageSpace"]){
        return "Yes"
      }else {
        return "No"
      }
    }else {
      return "Null";
    }
  }
  //checking whether the selected vehicle as storage
  sideCar():String {
    if (this.selectedVehicleDetails["type"] == "motorbike"){
      if (this.selectedVehicleDetails["sideCar"]){
        return "Yes"
      }else {
        return "No"
      }
    }else {
      return "Null";
    }
  }

}
