import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {EnrollmentBlock} from '../../models/enrollment/enrollment-block';
import {FieldOfStudy} from '../../models/enrollment/field-of-study';
import {EnrollmentDetails} from '../../models/enrollment/enrollment-details';
import {CourseItem} from '../../models/enrollment/course-item';

const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {

  baseUrl = environment.urlAddress + '/api/enrollment';

  constructor(private http: HttpClient) { }

  getFieldsOfStudy(studentIndex: string): Observable<FieldOfStudy[]> {
    return this.http.get<FieldOfStudy[]>(`${this.baseUrl}/fields/${studentIndex}`, {headers});
  }

  getEnrollmentBlocks(studentIndex: string, fieldOfStudyCode: string): Observable<EnrollmentBlock[]> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<EnrollmentBlock[]>(`${this.baseUrl}/blocks`, {headers, params});
  }

  getEnrollmentDetails(studentIndex: string, enrollmentBlockId: string): Observable<EnrollmentDetails> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('enrollmentBlockId', enrollmentBlockId);
    return this.http.get<EnrollmentDetails>(`${this.baseUrl}/details`, {headers, params});
  }

  getCurrentCourses(studentIndex: string, fieldOfStudyCode: string): Observable<CourseItem[]> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseItem[]>(`${this.baseUrl}/current-courses`, {headers, params});
  }

  getOverdueCourses(studentIndex: string, fieldOfStudyCode: string): Observable<CourseItem[]> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseItem[]>(`${this.baseUrl}/overdue-courses`, {headers, params});
  }
}
