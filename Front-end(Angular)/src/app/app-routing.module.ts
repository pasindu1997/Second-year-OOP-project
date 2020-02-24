import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {VehiclesComponent} from "./vehicles/vehicles.component";
import {NotAvailableComponent} from "./not-available/not-available.component";



const routes: Routes = [
  {
    path:"",
    component:VehiclesComponent,
  },
  {
    path:"**",//if no route is found
    component:NotAvailableComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
