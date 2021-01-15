import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { RoutingModule } from './routing/routing.module';
import { HeaderComponent } from './components/navigation/header/header.component';
import {EnrollmentModule} from './components/enrollment/enrollment.module';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    MaterialModule,
    RoutingModule,
    EnrollmentModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
