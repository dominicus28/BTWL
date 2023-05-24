import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ParcelsService {

  constructor(private http:HttpClient) { }

  getData() {
    let url="http://localhost:8080/parcels";
    return this.http.get(url);
  }

  getParcelDetails() {
    let url="http://ec2-44-199-249-212.compute-1.amazonaws.com/telemetries/6452ed8fef300344f2ebc5a3";
    return this.http.get(url);
  }

  getParcelsFromAddress() {
    let url="http://localhost:8080/delivery/from-address";
    return this.http.get(url);
  }

  getParcelsFromBin() {
    let url="http://localhost:8080/delivery/from-bin";
    return this.http.get(url);
  }

  sendMac(data: any) {
    let url="http://localhost:8080/pickup";
    return this.http.patch(url,data);
  }

  sendParcel(data: any){
    let url="http://localhost"
    return this.http.post(url,data)
  }
}
