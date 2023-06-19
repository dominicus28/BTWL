import { Component } from '@angular/core';
import {ParcelsService} from "../../parcels.service";
import {ActivatedRoute} from "@angular/router";
import { AppComponent } from "../../app.component";
import {LocalService} from "../../local.service";

@Component({
  selector: 'app-accept-parcel',
  templateUrl: './accept-parcel.component.html',
  styleUrls: ['./accept-parcel.component.css']
})
export class AcceptParcelComponent {
  display = false;
  data: any;
  isShowing: boolean = false;
  id: any;

  constructor(private parcel:ParcelsService, private route: ActivatedRoute, private app:AppComponent,  private localStore: LocalService) {
    this.id = this.route.snapshot.paramMap.get('id');
    this.parcel.getParcelDetails(this.id).subscribe(data=>{
      console.warn(data)
      this.data = data
    })
  }

  acceptParcel() {
    let login = this.localStore.getData('login')
    this.data = JSON.parse(`
    {
    "login": "${login}"
    }`)
    console.log(this.data)
    this.parcel.acceptParcel(this.data, this.id).subscribe((result)=> {
      console.log(result)
    }, err => {
      this.app.popup(err, "error")
    });
  }

  getMacFormData(data: any) {
    this.data = JSON.parse(`
    {
    "mac": "${data.mac}"
    }`)
    console.log(this.data)
    this.parcel.sendMac(this.data, this.id).subscribe((result)=> {
      console.log(result)
    }, err => {
      this.app.popup(err, "error")
    });
  }
  onPress() {
    this.display = !this.display;
  }


}
