import { Component } from '@angular/core';
import { UserService } from '../../user.service'
import {ParcelsService} from "../../parcels.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  display = false;

  constructor(private user:UserService) {

  }
  onPress() {
    this.display = !this.display;
  }

  getProfileFormData(data:any) {
    console.warn(data)
    this.user.updateUser(data).subscribe((result)=> {
      console.warn()
    })
  }
}
