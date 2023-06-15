import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-pickup',
  templateUrl: './pickup.component.html',
  styleUrls: ['./pickup.component.css']
})
export class PickupComponent {

  display = false;

  dataReceiver: any = [];
  constructor(private parcel:ParcelsService) {
    this.parcel.getDataReceiver().subscribe(data=>{
      console.warn(data)
      this.dataReceiver = data
    })

  }
  onPress() {
    this.display = !this.display;
  }

  // getSearchFormData(data: any) {
  //   this.data = JSON.parse(`{"mac": "${data.search}"}`)
  //   console.log(this.data)
  //   this.parcel.sendMac(this.data).subscribe((result)=> {
  //
  //   })
  // }

}
