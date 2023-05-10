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
