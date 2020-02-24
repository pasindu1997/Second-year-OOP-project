import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Schedule} from "../models/schedule";


@Injectable({
  providedIn: 'root'
})
export class APIService {
  private BASE_VEHICLES = `http://localhost:8080/api`;
  private ALL_VEHICLES = `${this.BASE_VEHICLES}\\allvehicles`;
  private PUTT_URL = `${this.BASE_VEHICLES}\\booking`;
  constructor(private http: HttpClient) {

  }
  //requesting the vehicles from the backend and returning it as a observable Document array
  getAllVehicles(): Observable<Document[]>{
    return this.http.get<Document[]>(this.ALL_VEHICLES);
  }
  //put requesting with a Schedule body and if from the url
  booking(schedule: Schedule): Observable<Schedule> {

    return this.http.put<Schedule>(`${this.PUTT_URL}/${schedule.id}`, schedule)
  }

}
