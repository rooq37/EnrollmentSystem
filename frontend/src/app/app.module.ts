import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material/material.module';
import {RoutingModule} from './routing/routing.module';
import {HeaderComponent} from './components/navigation/header/header.component';
import {EnrollmentModule} from './components/enrollment/enrollment.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FlexLayoutModule} from '@angular/flex-layout';
import {SuccessDialogComponent} from './components/dialog/success-dialog/success-dialog.component';
import {ErrorDialogComponent} from './components/dialog/error-dialog/error-dialog.component';
import {AuthModule} from '@auth0/auth0-angular';
import {TokenInterceptor} from "./interceptors/token-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SuccessDialogComponent,
    ErrorDialogComponent,
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    RoutingModule,
    EnrollmentModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AuthModule.forRoot({
      domain: 'dev-tcnqfecv.eu.auth0.com',
      clientId: 'EE5PVfSuzeb4gbg2Oh2JPEIP2tHPPdHb',
      audience: 'http://localhost:8080'
    })


  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
