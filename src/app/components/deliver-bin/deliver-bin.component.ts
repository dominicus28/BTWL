import { Component } from '@angular/core';

@Component({
  selector: 'app-deliver-bin',
  templateUrl: './deliver-bin.component.html',
  styleUrls: ['./deliver-bin.component.css']
})
export class DeliverBinComponent {
  display = false;

  onPress() {
    this.display = !this.display;
  }
}
