import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {EnrollmentService} from '../../../services/enrollment/enrollment.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {FieldOfStudy} from '../../../models/enrollment/field-of-study';
import {MatSelect, MatSelectChange} from '@angular/material/select';
import {EnrollmentBlock} from '../../../models/enrollment/enrollment-block';
import {EnrollmentDetails} from '../../../models/enrollment/enrollment-details';
import {CourseItem} from '../../../models/enrollment/course-item';

@Component({
  selector: 'app-enrollment',
  templateUrl: './enrollment.component.html',
  styleUrls: ['./enrollment.component.css']
})
export class EnrollmentComponent implements OnInit, AfterViewInit {
  blocks: EnrollmentBlock[];
  fieldsOfStudy: FieldOfStudy[];
  enrollmentDetails: EnrollmentDetails;
  public displayedColumns = ['name', 'code', 'formOfClasses', 'numberOfEcts', 'isSelectable'];
  public currentCourses = new MatTableDataSource<CourseItem>();
  public overdueCourses = new MatTableDataSource<CourseItem>();

  @ViewChild('enrollmentBlocks') enrollmentBlocks: MatSelect;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private enrollmentService: EnrollmentService) { }

  ngOnInit(): void {
    this.enrollmentService.getFieldsOfStudy('238123').subscribe(
      res => {
        this.fieldsOfStudy = res;
      }
    );
  }

  selectFieldOfStudy(event: MatSelectChange): void {
    this.enrollmentService.getEnrollmentBlocks('238123', event.value).subscribe(
      res => {
        this.blocks = res;
      }
    );
    this.enrollmentBlocks.value = '';
    this.enrollmentDetails = null;
    this.enrollmentService.getCurrentCourses('238123', event.value).subscribe(
      res => {
        this.currentCourses.data = res;
      }
    );
    this.enrollmentService.getOverdueCourses('238123', event.value).subscribe(
      res => {
        this.overdueCourses.data = res;
      }
    );
  }

  selectEnrollmentBlock(event: MatSelectChange): void {
    this.enrollmentService.getEnrollmentDetails('238123', event.value).subscribe(
      res => {
        this.enrollmentDetails = res;
      }
    );
  }

  ngAfterViewInit(): void {
    this.currentCourses.sort = this.sort;
  }

}
