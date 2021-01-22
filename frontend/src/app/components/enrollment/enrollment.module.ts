import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnrollmentComponent } from './enrollment/enrollment.component';
import { MaterialModule } from '../../material/material.module';
import {FlexLayoutModule} from '@angular/flex-layout';



@NgModule({
  declarations: [EnrollmentComponent],
    imports: [
        CommonModule,
        MaterialModule,
        FlexLayoutModule
    ]
})
export class EnrollmentModule { }
