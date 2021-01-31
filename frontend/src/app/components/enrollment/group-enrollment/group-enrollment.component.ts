import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CourseList} from '../../../models/enrollment/course-list';
import {GroupEnrollmentService} from '../../../services/enrollment/group-enrollment.service';
import {MatSelectChange} from '@angular/material/select';
import {MatTableDataSource} from '@angular/material/table';
import {GroupItem} from '../../../models/enrollment/group-item';
import {CourseWithGroup} from '../../../models/enrollment/course-with-group';
import {MatSort, Sort} from '@angular/material/sort';
import {SuccessHandlerService} from '../../../services/dialog/success-handler.service';
import {ErrorHandlerService} from '../../../services/dialog/error-handler.service';
import {UserService} from "../../../services/user/user.service";

function compare(a: number | string | boolean, b: number | string | boolean, isAsc: boolean): number {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

@Component({
  selector: 'app-group-enrollment',
  templateUrl: './group-enrollment.component.html',
  styleUrls: ['./group-enrollment.component.css']
})
export class GroupEnrollmentComponent implements OnInit, AfterViewInit {
  enrollmentBlockId: string;
  fieldOfStudyCode: string;
  courseList: CourseList;
  public displayedColumnsGroups = ['date', 'lecturers', 'place', 'occupancy', 'code', 'action'];
  public displayedColumnsCoursesWithGroups =
    ['courseName', 'formOfClasses', 'numberOfEcts', 'isSelectable', 'date', 'lecturers', 'place', 'occupancy', 'groupCode', 'action'];
  public groups = new MatTableDataSource<GroupItem>();
  public currentCoursesWithGroups = new MatTableDataSource<CourseWithGroup>();
  public overdueCoursesWithGroups = new MatTableDataSource<CourseWithGroup>();

  private selectedCourseCode: string;

  @ViewChild('groupsSort') groupsSort: MatSort;
  @ViewChild('currentCoursesWithGroupsSort') currentCoursesWithGroupsSort: MatSort;
  @ViewChild('overdueCoursesWithGroupsSort') overdueCoursesWithGroupsSort: MatSort;

  constructor(private groupEnrollmentService: GroupEnrollmentService,
              private activeRoute: ActivatedRoute,
              private successHandler: SuccessHandlerService,
              private errorHandler: ErrorHandlerService) { }

  ngOnInit(): void {
    this.enrollmentBlockId = this.activeRoute.snapshot.params.enrollmentBlockId;
    this.fieldOfStudyCode = this.activeRoute.snapshot.params.fieldOfStudyCode;
    this.groupEnrollmentService.getCourses( this.fieldOfStudyCode, this.enrollmentBlockId).subscribe(
      res => {
        this.courseList = res;
      }
    );
    this.updateCurrentCourses();
    this.updateOverdueCourses();
  }

  selectCourse(event: MatSelectChange): void {
    this.selectedCourseCode = event.value;
    this.getGroups(this.selectedCourseCode);
  }

  subscribeToTheGroup(groupCode: string): void {
    const subscription = {
      groupCode,
      enrollmentBlockId: this.enrollmentBlockId
    };
    this.groupEnrollmentService.subscribeToTheGroup(subscription).subscribe(
      res => {
        this.successHandler.openSuccessDialog(res.message);
        this.updateCurrentCourses();
        this.updateOverdueCourses();
        this.refreshGroups();
      },
      err => {
        this.errorHandler.openErrorDialog(err.error.message);
      }
    );
  }

  unsubscribeFromTheGroup(groupCode: string): void {
    this.groupEnrollmentService.unsubscribeFromTheGroup(groupCode).subscribe(
      res => {
        this.successHandler.openSuccessDialog(res.message);
        this.updateCurrentCourses();
        this.updateOverdueCourses();
        this.refreshGroups();
      },
      err => {
        this.errorHandler.openErrorDialog(err.error.message);
      }
    );
  }

  ngAfterViewInit(): void {
    this.groups.sort = this.groupsSort;
    this.currentCoursesWithGroups.sort = this.currentCoursesWithGroupsSort;
    this.overdueCoursesWithGroups.sort = this.overdueCoursesWithGroupsSort;
  }

  private updateCurrentCourses(): void {
    this.groupEnrollmentService.getCurrentCoursesWithGroup(this.fieldOfStudyCode).subscribe(
      res => {
        this.currentCoursesWithGroups.data = res;
      }
    );
  }

  private updateOverdueCourses(): void {
    this.groupEnrollmentService.getOverdueCoursesWithGroup(this.fieldOfStudyCode).subscribe(
      res => {
        this.overdueCoursesWithGroups.data = res;
      }
    );
  }

  private getGroups(courseId: string): void {
    this.groupEnrollmentService.getGroups(courseId, this.enrollmentBlockId).subscribe(
      res => {
        this.groups.data = res;
      }
    );
  }

  private refreshGroups(): void {
    this.getGroups(this.selectedCourseCode);
  }

  sortGroups(sort: Sort): void {
    const data = this.groups.data.slice();
    if (!sort.active || sort.direction === '') {
      this.groups.data = data;
      return;
    }


    this.groups.data = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'date': return compare(a.date, b.date, isAsc);
        case 'lecturers': return compare(a.lecturers, b.lecturers, isAsc);
        case 'place': return compare(a.place, b.place, isAsc);
        case 'occupancy': return compare(a.occupancy, b.occupancy, isAsc);
        case 'code': return compare(a.code, b.code, isAsc);
        default: return 0;
      }
    });
  }

  sortCurrentCourses(sort: Sort): void {
    const data = this.currentCoursesWithGroups.data.slice();
    if (!sort.active || sort.direction === '') {
      this.currentCoursesWithGroups.data = data;
      return;
    }


    this.currentCoursesWithGroups.data = data.sort(this.compareCourses(sort));
  }

  sortOverdueCourses(sort: Sort): void {
    const data = this.overdueCoursesWithGroups.data.slice();
    if (!sort.active || sort.direction === '') {
      this.overdueCoursesWithGroups.data = data;
      return;
    }

    this.overdueCoursesWithGroups.data = data.sort(this.compareCourses(sort));
  }

  private compareCourses(sort: Sort): (a: CourseWithGroup, b: CourseWithGroup) => number {
    const isAsc = sort.direction === 'asc';
    return (({ course: courseA, group: groupA}, { course: courseB , group: groupB}) => {
      switch (sort.active) {
        case 'courseName': return compare(courseA.name, courseB.name, isAsc);
        case 'formOfClasses': return compare(courseA.formOfClasses, courseB.formOfClasses, isAsc);
        case 'numberOfEcts': return compare(courseA.numberOfEcts, courseB.numberOfEcts, isAsc);
        case 'isSelectable': return compare(courseA.isSelectable, courseB.isSelectable, isAsc);
        case 'date': return compare(groupB?.date, groupB?.date, isAsc);
        case 'lecturers': return compare(groupB?.lecturers, groupB?.lecturers, isAsc);
        case 'place': return compare(groupB?.place, groupB?.place, isAsc);
        case 'occupancy': return compare(groupB?.occupancy, groupB?.occupancy, isAsc);
        case 'groupCode': return compare(groupB?.code, groupB?.code, isAsc);
        default: return 0;
      }
    });
  }
}
