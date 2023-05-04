import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  updateUser(data:any){
    let url="http://localhost"
    return this.http.patch(url,data)
  }
}
