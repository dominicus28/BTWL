import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { WelcomeAnimationComponent } from './components/welcome-animation/welcome-animation.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { SendComponent } from './components/send/send.component';
import { SendDetailsComponent } from './components/send-details/send-details.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DeliverComponent } from './components/deliver/deliver.component';
import { PickupComponent } from './components/pickup/pickup.component';
import { ParcelDetailsComponent } from './components/parcel-details/parcel-details.component';
import { AcceptParcelComponent } from './components/accept-parcel/accept-parcel.component';
import {ParcelDetailsCourierComponent} from "./components/parcel-details-courier/parcel-details-courier.component";
import {ParcelDetailsReceiverComponent} from "./components/parcel-details-receiver/parcel-details-receiver.component";

const routes: Routes = [
  { path: '', component: WelcomeAnimationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'send', component: SendComponent },
  { path: 'send-details', component: SendDetailsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'deliver', component: DeliverComponent },
  { path: 'pickup', component: PickupComponent },
  { path: 'parcel-details/:id', component: ParcelDetailsComponent },
  { path: 'accept-parcel/:id', component: AcceptParcelComponent },
  { path: 'parcel-details-courier/:id', component: ParcelDetailsCourierComponent },
  { path: 'parcel-details-receiver/:id', component: ParcelDetailsReceiverComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
