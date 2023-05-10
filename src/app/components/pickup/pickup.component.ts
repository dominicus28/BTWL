import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-pickup',
  templateUrl: './pickup.component.html',
  styleUrls: ['./pickup.component.css']
})
export class PickupComponent {

  display = false;
  data: String = "";

  constructor(private parcel:ParcelsService) {

  }
  onPress() {
    this.display = !this.display;
  }

  getSearchFormData(data: any) {
    this.data = JSON.parse(`{"mac": "${data.search}"}`)
    console.log(this.data)
    this.parcel.sendMac(this.data).subscribe((result)=> {

    })
  }

}
