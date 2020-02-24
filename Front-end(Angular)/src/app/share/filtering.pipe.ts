import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtering'
})
export class FilteringPipe implements PipeTransform {

  transform(vehicles: Document[], search: String): Document[] {
    //if the search input value is equal or null it returns all the vehicles in the array
    if (search == null || search === ""){
      return vehicles;
    }
    //if the search has a string then the vehicles will be filtered with the make parameter
    return vehicles.filter(a=>a["model"] == search || a["make"] == search || a["model"] == search.toUpperCase() || a["make"] == search.toUpperCase());

  }

}
