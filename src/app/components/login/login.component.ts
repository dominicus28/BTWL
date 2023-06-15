import { Component } from '@angular/core';
import {LocalService} from "../../local.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private localStore: LocalService) {

  }

  loginUser(data: any) {
    this.localStore.saveData('login',data.login)
  }

}
