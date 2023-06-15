import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'btwl-app';
  messageJSON: any;
  showMess: boolean = false;


  popup(result: any, flag: string){
    let mess = JSON.stringify(result)
    if (flag == "ok") {
      this.messageJSON = JSON.parse(mess).message
    } else {
      this.messageJSON = JSON.parse(mess).error.message
    }
    this.showMess = true
    console.log(this.messageJSON)
  }
}
