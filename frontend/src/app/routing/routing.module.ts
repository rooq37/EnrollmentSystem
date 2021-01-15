import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {EnrollmentComponent} from '../components/enrollment/enrollment/enrollment.component';

const routes: Routes = [
  { path: 'enrollment', component: EnrollmentComponent},
  { path: '', redirectTo: '/enrollment', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class RoutingModule { }
