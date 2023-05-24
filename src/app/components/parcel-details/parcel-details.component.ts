import { Component } from '@angular/core';
import * as L from 'leaflet';
import {ParcelsService} from "../../parcels.service";

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

  data: any;
  // @ts-ignore
  longitude: number;
  // @ts-ignore
  latitude: number;

  constructor(private parcel:ParcelsService) {
    this.parcel.getParcelDetails().subscribe(data=>{
      console.warn(data)
      this.data = data
      this.longitude = parseFloat(this.data.longitude)
      this.latitude = parseFloat(this.data.latitude)
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

  // ngOnInit(): void {
  //   this.initMap();
  //   console.warn(this.latitude)
  // }
}
