import { Component } from '@angular/core';
import {LocalService} from "../../local.service";

@Component({
  selector: 'app-deliver',
  templateUrl: './deliver.component.html',
  styleUrls: ['./deliver.component.css']
})
export class DeliverComponent {
  display = false;
  display_details = "";
  delivery = "";

  constructor(private localStore: LocalService) {

  }

  onSwitch(delivery: string) {
    this.display_details = delivery;
  }

  onClickSubmit(delivery: string) {
    this.localStore.saveData('delivery-option',delivery)
  }

  setDeliveryProperty(delivery: string) {
    this.delivery = delivery;
    this.onClickSubmit(delivery);
    this.onSwitch(delivery);
  }
  onPress() {
    this.display = !this.display;
  }
}
