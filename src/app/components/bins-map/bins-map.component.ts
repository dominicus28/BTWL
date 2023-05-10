import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-bins-map',
  templateUrl: './bins-map.component.html',
  styleUrls: ['./bins-map.component.css']
})
export class BinsMapComponent implements OnInit {
  // @ts-ignore
  private map: L.Map;
  private centroid: L.LatLngExpression = [51.107883, 17.038538]; //wrocław

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

    L.marker([51.18, 17.04]).addTo(this.map)
      .bindPopup('Paczkomat Wrocław 1<br><button style="">Wybierz</button>')
      .openPopup();

    L.marker([51.09, 16.99]).addTo(this.map)
      .bindPopup('Paczkmat Wrocław 2<br><button>Wybierz</button>')
      .openPopup();

    tiles.addTo(this.map);

  }

  constructor() { }

  ngOnInit(): void {
    this.initMap();
  }
}
