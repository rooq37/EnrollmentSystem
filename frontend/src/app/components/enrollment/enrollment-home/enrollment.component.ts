import {
  AfterViewInit,
  Component,
  OnInit,
  ViewChild
} from '@angular/core';
import {EnrollmentService} from '../../../services/enrollment/enrollment.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {FieldOfStudy} from '../../../models/enrollment/field-of-study';
import {MatSelect, MatSelectChange} from '@angular/material/select';
import {EnrollmentBlock} from '../../../models/enrollment/enrollment-block';
import {EnrollmentDetails} from '../../../models/enrollment/enrollment-details';
import {CourseItem} from '../../../models/enrollment/course-item';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UserService} from '../../../services/user/user.service';
import {Observable} from "rxjs";

@Component({
  selector: 'app-enrollment',
  templateUrl: './enrollment.component.html',
  styleUrls: ['./enrollment.component.css']
})
export class EnrollmentComponent implements OnInit, AfterViewInit {
  blocks: EnrollmentBlock[];
  fieldsOfStudy$: Observable<FieldOfStudy[]>;
  enrollmentDetails: EnrollmentDetails;
  enrollmentForm: FormGroup;
  userService: UserService;


  public displayedColumns = ['name', 'code', 'formOfClasses', 'numberOfEcts', 'isSelectable'];
  public currentCourses = new MatTableDataSource<CourseItem>();
  public overdueCourses = new MatTableDataSource<CourseItem>();

  @ViewChild('enrollmentBlocks') enrollmentBlocks: MatSelect;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private enrollmentService: EnrollmentService,
              private router: Router,
              userService: UserService) {
    this.userService = userService;
  }

  ngOnInit(): void {
    this.fieldsOfStudy$ = this.enrollmentService.getFieldsOfStudy();
    this.enrollmentForm = new FormGroup({
      fieldOfStudy: new FormControl('', [Validators.required]),
      enrollmentBlock: new FormControl('', [Validators.required])
    });
  }

  selectFieldOfStudy(event: MatSelectChange): void {
    this.enrollmentService.getEnrollmentBlocks(event.value).subscribe(
      res => {
        this.blocks = res;
      }
    );
    this.enrollmentForm.controls.enrollmentBlock.setValue(null);
    this.enrollmentDetails = null;
    this.enrollmentService.getCurrentCourses( event.value).subscribe(
      res => {
        this.currentCourses.data = res;
      }
    );
    this.enrollmentService.getOverdueCourses(event.value).subscribe(
      res => {
        this.overdueCourses.data = res;
      }
    );
  }

  selectEnrollmentBlock(event: MatSelectChange): void {
    this.enrollmentService.getEnrollmentDetails( event.value).subscribe(
      res => {
        this.enrollmentDetails = res;
      }
    );
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.enrollmentForm.controls[controlName].hasError(errorName);
  }

  redirectToGroupEnrollment(): void {
    const enrollmentBlockId = this.enrollmentForm.controls.enrollmentBlock.value;
    const fieldOfStudyCode = this.enrollmentForm.controls.fieldOfStudy.value;
    const url = `/group-enrollment/${fieldOfStudyCode}/${enrollmentBlockId}`;
    this.router.navigate([url]);
  }

  ngAfterViewInit(): void {
    this.currentCourses.sort = this.sort;
  }

}
