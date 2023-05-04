import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-send-details-pp',
  templateUrl: './send-details-pp.component.html',
  styleUrls: ['./send-details-pp.component.css']
})
export class SendDetailsPPComponent {
  display = false;
  data: String = "";

  constructor(private parcel:ParcelsService) {

  }

  onPress() {
    this.display = !this.display;
  }

  getSendFormData(data: any) {
    this.data = JSON.parse(`{"senderId": "${data.senderId}", "receiverId": "${data.receiverId}",
    "size": "${localStorage.getItem('size')}", "delivery": "${localStorage.getItem('delivery')}"}`)
    console.log(this.data)
    this.parcel.sendParcel(this.data).subscribe((result)=> {

    })
  }

}
