import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeAnimationComponent } from './components/welcome-animation/welcome-animation.component';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { MenuComponent } from './components/menu/menu.component';
import { AppUiModule } from './app-ui.module';
import { HttpClientModule } from '@angular/common/http';
import { SendComponent } from './components/send/send.component';
import { SendDetailsComponent } from './components/send-details/send-details.component';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from './components/profile/profile.component';
import { SendDetailsLPComponent } from './components/send-details-lp/send-details-lp.component';
import { SendDetailsPLComponent } from './components/send-details-pl/send-details-pl.component';
import { SendDetailsPPComponent } from './components/send-details-pp/send-details-pp.component'

@NgModule({
  declarations: [
    AppComponent,
    WelcomeAnimationComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    MenuComponent,
    SendComponent,
    SendDetailsComponent,
    ProfileComponent,
    SendDetailsLPComponent,
    SendDetailsPLComponent,
    SendDetailsPPComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppUiModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
