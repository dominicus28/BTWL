import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-send-details-pl',
  templateUrl: './send-details-pl.component.html',
  styleUrls: ['./send-details-pl.component.css']
})
export class SendDetailsPLComponent {
  display = false;
  data: String = "";

  constructor(private parcel:ParcelsService) {

  }

  onPress() {
    this.display = !this.display;
  }

  getSendFormData(data: any) {
    this.data = JSON.parse(`{"senderId": ${data.senderId}, "receiverName": "${data.receiverName}", "receiverMail": "${data.receiverMail}", "receiverTelephone": ${data.receiverTelephone},
    "receiverPostal": ${data.receiverPostal}, "receiverPlace": "${data.receiverPlace}", "receiverStreet": "${data.receiverStreet}",
    "size": "${localStorage.getItem('size')}", "delivery": "${localStorage.getItem('delivery')}"}`)
    console.log(this.data)
    this.parcel.sendParcel(this.data).subscribe((result)=> {

    })
  }

}
