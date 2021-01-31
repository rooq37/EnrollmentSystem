import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnrollmentComponent } from './enrollment-home/enrollment.component';
import { MaterialModule } from '../../material/material.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import { GroupEnrollmentComponent } from './group-enrollment/group-enrollment.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [EnrollmentComponent, GroupEnrollmentComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule
  ]
})
export class EnrollmentModule { }
