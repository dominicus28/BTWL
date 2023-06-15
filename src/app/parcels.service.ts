import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalService} from "./local.service";


@Injectable({
  providedIn: 'root'
})
export class ParcelsService {
  //@ts-ignore
  login: any;

  constructor(private http:HttpClient, private localStore: LocalService) {
    this.login = this.localStore.getData('login')
  }

  getData() {
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/users/${this.login}/senders/parcels`;
    return this.http.get(url);
  }

  getDataCourier() {
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/users/${this.login}/couriers/parcels`;
    return this.http.get(url);
  }

  getDataReceiver() {
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/users/${this.login}/receivers/parcels`;
    return this.http.get(url);
  }

  getParcelDetails(id: any) {
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/${id}`;
    return this.http.get(url);
  }

  getParcelsFromAddress(country: string, province: string, city: string) {
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/regions/${country}/${province}/${city}/parcels`;
    return this.http.get(url);
  }

  getParcelsFromBin() {
    let url="http://localhost:8080/delivery/from-bin";
    return this.http.get(url);
  }

  sendParcel(data: any){
    let url="http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a"
    return this.http.post(url,data)
  }

  acceptParcel(data: any, id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/courier/1/accept`
    return this.http.put(url,data)
  }

  sendMac(data: any, id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/courier/3/box/assign`
    return this.http.put(url,data)
  }

  openParcel(id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/courier/3/box/open`
    return this.http.put(url, "")
  }
  closeParcel(id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/sender/3/box/protect`
    return this.http.put(url, "")
  }
  deliverParcel(id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/courier/5/box/open`
    return this.http.put(url, "")
  }
  accessParcel(id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/receiver/5/box/open`
    return this.http.put(url, "")
  }
  endTransaction(id: string){
    let url=`http://ec2-3-215-18-200.compute-1.amazonaws.com/parcels/a2a/${id}/receiver/5/end`
    return this.http.put(url, "")
  }
}
