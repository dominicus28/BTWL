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
}
