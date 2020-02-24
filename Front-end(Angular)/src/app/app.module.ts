import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { NotAvailableComponent } from './not-available/not-available.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import {HttpClientModule} from "@angular/common/http";
import { FilteringPipe } from './share/filtering.pipe';
import {FormsModule} from "@angular/forms";
import { BookingsComponent } from './bookings/bookings.component';
import {MatGridListModule} from "@angular/material/grid-list";

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    NotAvailableComponent,
    VehiclesComponent,
    FilteringPipe,
    BookingsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
