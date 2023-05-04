import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { WelcomeAnimationComponent } from './components/welcome-animation/welcome-animation.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { SendComponent } from './components/send/send.component';
import { SendDetailsComponent } from './components/send-details/send-details.component';
import { ProfileComponent } from './components/profile/profile.component'

const routes: Routes = [
  { path: '', component: WelcomeAnimationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'send', component: SendComponent },
  { path: 'send-details', component: SendDetailsComponent },
  { path: 'profile', component: ProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
