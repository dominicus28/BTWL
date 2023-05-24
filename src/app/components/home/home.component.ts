import {Component} from '@angular/core';
import {ParcelsService} from '../../parcels.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent {
  display = false;
  data: any = [];

  constructor(private parcel:ParcelsService) {

    this.parcel.getData().subscribe(data=>{
      console.warn(data)
      this.data = data
    })
  }
  packagesList = [
    {
      to: "Jan Kowalski",
      id: 110043,
      from: "Wrocław",
      destination: "Leśnica",
      status: "czeka na umieszczenie w BlackBoxie"
    },
    {
      to: "Mirosław Close",
      id: 987313,
      from: "Wrocław",
      destination: "Kąty Wrocławskie",
      status: "w drodze"
    },
    {
      to: "Ilona Nowak",
      id: 12223,
      from: "Wrocław Psie Pole",
      destination: "Wrocław Muchobór",
      status: "czeka na ofertę"
    }
  ]
  onPress() {
    this.display = !this.display;
  }

}
