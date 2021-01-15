import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnrollmentComponent } from './enrollment/enrollment.component';
import { MaterialModule } from '../../material/material.module';



@NgModule({
  declarations: [EnrollmentComponent],
  imports: [
    CommonModule,
    MaterialModule
  ]
})
export class EnrollmentModule { }
