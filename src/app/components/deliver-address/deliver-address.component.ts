import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-deliver-address',
  templateUrl: './deliver-address.component.html',
  styleUrls: ['./deliver-address.component.css']
})
export class DeliverAddressComponent {
  display = false;
  data: any = [];

  constructor(private parcel:ParcelsService) {

  }


  getSearchFormData(data: any) {
    let country = data.country
    let province = data.province
    let city = data.city
    this.parcel.getParcelsFromAddress(country, province, city).subscribe(data=> {
      console.warn(data)
      this.data = data
    })
  }
  onPress() {
    this.display = !this.display;
  }

}
