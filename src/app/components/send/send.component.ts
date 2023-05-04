import { Component } from '@angular/core';
import { LocalService } from '../../local.service';

@Component({
  selector: 'app-send',
  templateUrl: './send.component.html',
  styleUrls: ['./send.component.css']
})
export class SendComponent {
  display = false;
  display_details = "";
  delivery = "";

  constructor(private localStore: LocalService) {

  }
  onPress() {
    this.display = !this.display;
  }
  onSwitch(delivery: string) {
    this.display_details = delivery;
  }

  setDeliveryProperty(delivery: string, size: object) {
    this.delivery = delivery;
    this.onSwitch(delivery)
    this.onClickSubmit(size, delivery)
  }
  onClickSubmit(size: object, delivery: string) {
    this.localStore.saveData('size',size.toString())
    this.localStore.saveData('delivery',delivery.toString())
  }

}
