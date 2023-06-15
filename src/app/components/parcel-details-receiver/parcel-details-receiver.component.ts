import { Component } from '@angular/core';
import * as L from "leaflet";
import {ParcelsService} from "../../parcels.service";
import {ActivatedRoute} from "@angular/router";
import {popup} from "leaflet";

@Component({
  selector: 'app-parcel-details-receiver',
  templateUrl: './parcel-details-receiver.component.html',
  styleUrls: ['./parcel-details-receiver.component.css']
})
export class ParcelDetailsReceiverComponent {
  // @ts-ignore
  private map: L.Map;
  private centroid: L.LatLngExpression = [51.107883, 17.038538]; //wrocław
  display = false;
  isShowing: boolean = false;
  showMess: boolean = false;
  messageJSON: any;
  data: any = [];
  // @ts-ignore
  longitude: number;
  // @ts-ignore
  latitude: number;

  id: any;

  constructor(private parcel:ParcelsService, private route: ActivatedRoute) {
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
      .bindPopup('Tutaj znajduje się obecnie paczka')
      .openPopup();

    tiles.addTo(this.map);

  }
  accessParcel() {
    this.parcel.accessParcel(this.id).subscribe((result)=> {
      popup(result)
    })
  }
  endTransaction() {
    this.parcel.endTransaction(this.id).subscribe((result)=> {
      popup(result)
    })
  }
  popup(result: any){
    let mess = JSON.stringify(result)
    this.messageJSON = JSON.parse(mess).message
    console.log(this.messageJSON)
  }

}
