import { Component} from '@angular/core';
import {ParcelsService} from '../../parcels.service'

@Component({
  selector: 'app-send-details',
  templateUrl: './send-details.component.html',
  styleUrls: ['./send-details.component.css']
})
export class SendDetailsComponent {
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
    "receiverName": "${data.receiverName}", "receiverMail": "${data.receiverMail}", "receiverTelephone": ${data.receiverTelephone},
    "receiverPostal": ${data.receiverPostal}, "receiverPlace": "${data.receiverPlace}", "receiverStreet": "${data.receiverStreet}"}`)
    console.log(this.data)
    this.parcel.sendParcel(this.data).subscribe((result)=> {

    })
  }

}
