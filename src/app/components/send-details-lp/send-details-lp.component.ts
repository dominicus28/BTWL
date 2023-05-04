import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-send-details-lp',
  templateUrl: './send-details-lp.component.html',
  styleUrls: ['./send-details-lp.component.css']
})
export class SendDetailsLPComponent {
  display = false;
  data: String = "";

  constructor(private parcel:ParcelsService) {

  }

  onPress() {
    this.display = !this.display;
  }

  getSendFormData(data: any) {
    this.data = JSON.parse(`{"name": "${data.name}", "mail": "${data.mail}", "telephone": ${data.telephone},
    "postal": ${data.postal}, "place": "${data.place}", "street": "${data.street}",
    "size": "${localStorage.getItem('size')}", "delivery": "${localStorage.getItem('delivery')}",
    "receiverId": ${data.receiverId}}`)
    console.log(this.data)
    this.parcel.sendParcel(this.data).subscribe((result)=> {

    })
  }

}
