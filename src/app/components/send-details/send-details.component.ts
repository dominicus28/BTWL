import { Component} from '@angular/core';
import {ParcelsService} from '../../parcels.service'
import {LocalService} from "../../local.service";

@Component({
  selector: 'app-send-details',
  templateUrl: './send-details.component.html',
  styleUrls: ['./send-details.component.css']
})
export class SendDetailsComponent {
  display = false;
  data: String = "";

  constructor(private parcel:ParcelsService, private localStore: LocalService) {

  }

  onPress() {
    this.display = !this.display;
  }

  getSendFormData(data: any) {
    let login = this.localStore.getData('login')
    this.data = JSON.parse(`
    {
    "size": "A",
    "deliverFrom": {
      "region":{
        "postalCode":"${data.postal}",
        "city": "${data.city}"
      },
      "street":"${data.street}",
      "nrOfHouse":"${data.nrOfHouse}"
    },
    "deliverTo":{
      "region":{
        "postalCode":"${data.receiverPostal}",
        "city": "${data.cityReceiver}"
      },
      "street":"${data.receiverStreet}",
      "nrOfHouse":"${data.nrOfHouseReceiver}"
    },
    "insurance": 1000,
    "sender":{"login": "${login}"},
    "receiver":{"login": "${data.receiverLogin}"}
    }`)
    // "size": "${localStorage.getItem('size')}", "delivery": "${localStorage.getItem('delivery')}",
    // "receiverName": "${data.receiverName}", "receiverMail": "${data.receiverMail}", "receiverTelephone": ${data.receiverTelephone},
    // "receiverPostal": ${data.receiverPostal}, "receiverPlace": "${data.receiverPlace}", "receiverStreet": "${data.receiverStreet}"}`)
    console.log(this.data)
    this.parcel.sendParcel(this.data).subscribe((result)=> {

    })
  }

}
