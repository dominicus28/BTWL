import { Component } from '@angular/core';
import * as L from 'leaflet';
import {ParcelsService} from "../../parcels.service";
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from "../../app.component";

@Component({
  selector: 'app-parcel-details',
  templateUrl: './parcel-details.component.html',
  styleUrls: ['./parcel-details.component.css']
})
export class ParcelDetailsComponent {
  // @ts-ignore
  private map: L.Map;
  private centroid: L.LatLngExpression = [51.107883, 17.038538]; //wrocław
  display = false;
  isShowing: boolean = false;
  showMess: boolean = false;
  data: any;
  // @ts-ignore
  longitude: number;
  // @ts-ignore
  latitude: number;
  messageJSON: any;

  id: any;

  constructor(private parcel:ParcelsService, private route: ActivatedRoute, private app:AppComponent) {
    this.id = this.route.snapshot.paramMap.get('id');
    this.parcel.getParcelDetails(this.id).subscribe(data=>{
      console.warn(data)
      this.data = data
      this.longitude = parseFloat(this.data.telemetry[this.data.telemetry.length-1].telemetry.longitude)
      this.latitude = parseFloat(this.data.telemetry[this.data.telemetry.length-1].telemetry.latitude)
      this.initMap();
    })
  }

  onPress() {
    this.display = !this.display;
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: this.centroid,
      zoom: 10
    });

    const tiles = L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      // maxZoom: 3,
      // minZoom: 1,
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    L.marker([this.latitude, this.longitude]).addTo(this.map)
      .bindPopup('Tutaj znajduje się obecnie twoja paczka')
      .openPopup();

    tiles.addTo(this.map);

  }
  closeParcel() {
    this.parcel.closeParcel(this.id).subscribe((result)=> {
      this.app.popup(result, "ok")
    }, err => {
      this.app.popup(err, "error")
    });
  }


}
