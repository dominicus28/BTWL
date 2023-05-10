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


  // getSearchFormData(data: any) {
  //   this.data = JSON.parse(`{"location": "${data.search}"}`)
  //   console.log(this.data)
  //   this.parcel.getParcelsFromAddress(this.data).subscribe((result)=> {
  //
  //   })
  // }
  onPress() {
    this.display = !this.display;
  }

}
