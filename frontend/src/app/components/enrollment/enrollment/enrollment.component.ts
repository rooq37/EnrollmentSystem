import { Component, OnInit } from '@angular/core';
import {EnrollmentService} from '../../../services/enrollment/enrollment.service';

@Component({
  selector: 'app-enrollment',
  templateUrl: './enrollment.component.html',
  styleUrls: ['./enrollment.component.css']
})
export class EnrollmentComponent implements OnInit {
  blocks: string[];

  constructor(private enrollmentService: EnrollmentService) { }

  ngOnInit(): void {
    this.enrollmentService.getEnrollmentBlocks('238123').subscribe(
      res => {
        this.blocks = res.enrollmentBlockNames;
      }
    );
  }

}
