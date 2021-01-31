import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {CourseList} from '../../models/enrollment/course-list';
import {GroupItem} from '../../models/enrollment/group-item';
import {CourseWithGroup} from '../../models/enrollment/course-with-group';
import {Message} from '../../models/message';
import {Subscription} from '../../models/enrollment/subscription';

const headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

@Injectable({
  providedIn: 'root'
})
export class GroupEnrollmentService {

  baseUrl = environment.urlAddress + '/api/group-enrollment';

  constructor(private http: HttpClient) { }

  getCourses(studentIndex: string, fieldOfStudyCode: string, enrollmentBlockId: string): Observable<CourseList> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('fieldOfStudyCode', fieldOfStudyCode)
      .set('enrollmentBlockId', enrollmentBlockId);
    return this.http.get<CourseList>(`${this.baseUrl}/courses`, {headers, params});
  }

  getGroups(studentIndex: string, courseCode: string, enrollmentBlockId: string): Observable<GroupItem[]> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('courseCode', courseCode)
      .set('enrollmentBlockId', enrollmentBlockId);
    return this.http.get<GroupItem[]>(`${this.baseUrl}/groups`, {headers, params});
  }

  getCurrentCoursesWithGroup(studentIndex: string, fieldOfStudyCode: string): Observable<CourseWithGroup[]> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseWithGroup[]>(`${this.baseUrl}/current-courses-with-groups`, {headers, params});
  }

  getOverdueCoursesWithGroup(studentIndex: string, fieldOfStudyCode: string): Observable<CourseWithGroup[]> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseWithGroup[]>(`${this.baseUrl}/overdue-courses-with-groups`, {headers, params});
  }

  subscribeToTheGroup(subscription: Subscription): Observable<Message> {
    return this.http.post<Message>(`${this.baseUrl}/subscription`, subscription, {headers});
  }

  unsubscribeFromTheGroup(studentIndex: string, groupCode: string): Observable<Message> {
    const params = new HttpParams()
      .set('studentIndex', studentIndex)
      .set('groupCode', groupCode);
    return this.http.delete<Message>(`${this.baseUrl}/subscription`, {headers, params});
  }
}
