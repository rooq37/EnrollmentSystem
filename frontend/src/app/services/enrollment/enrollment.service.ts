import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {EnrollmentBlock} from '../../models/enrollment/enrollment-block';
import {FieldOfStudy} from '../../models/enrollment/field-of-study';
import {EnrollmentDetails} from '../../models/enrollment/enrollment-details';
import {CourseItem} from '../../models/enrollment/course-item';
import {UserService} from "../user/user.service";
import {switchMap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {

  baseUrl = environment.urlAddress + '/api/enrollment';

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getFieldsOfStudy(): Observable<FieldOfStudy[]> {
    return this.userService.index().pipe(
      switchMap(index => this.http.get<FieldOfStudy[]>(`${this.baseUrl}/fields/${index}`))
    );
  }

  getEnrollmentBlocks(fieldOfStudyCode: string): Observable<EnrollmentBlock[]> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<EnrollmentBlock[]>(`${this.baseUrl}/blocks`, {params});
  }

  getEnrollmentDetails(enrollmentBlockId: string): Observable<EnrollmentDetails> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('enrollmentBlockId', enrollmentBlockId);
    return this.http.get<EnrollmentDetails>(`${this.baseUrl}/details`, {params});
  }

  getCurrentCourses(fieldOfStudyCode: string): Observable<CourseItem[]> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseItem[]>(`${this.baseUrl}/current-courses`, {params});
  }

  getOverdueCourses(fieldOfStudyCode: string): Observable<CourseItem[]> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseItem[]>(`${this.baseUrl}/overdue-courses`, {params});
  }
}
