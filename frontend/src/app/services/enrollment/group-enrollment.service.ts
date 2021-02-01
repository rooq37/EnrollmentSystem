import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {CourseList} from '../../models/enrollment/course-list';
import {GroupItem} from '../../models/enrollment/group-item';
import {CourseWithGroup} from '../../models/enrollment/course-with-group';
import {Message} from '../../models/message';
import {Subscription} from '../../models/enrollment/subscription';
import {UserService} from '../user/user.service';


@Injectable({
  providedIn: 'root'
})
export class GroupEnrollmentService {

  baseUrl = environment.urlAddress + '/api/group-enrollment';

  constructor(private http: HttpClient, private userService: UserService) { }


  getCourses(fieldOfStudyCode: string, enrollmentBlockId: string): Observable<CourseList> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('fieldOfStudyCode', fieldOfStudyCode)
      .set('enrollmentBlockId', enrollmentBlockId);
    return this.http.get<CourseList>(`${this.baseUrl}/courses`, {params});
  }

  getGroups(courseCode: string, enrollmentBlockId: string): Observable<GroupItem[]> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('courseCode', courseCode)
      .set('enrollmentBlockId', enrollmentBlockId);
    return this.http.get<GroupItem[]>(`${this.baseUrl}/groups`, {params});
  }

  getCurrentCoursesWithGroup(fieldOfStudyCode: string): Observable<CourseWithGroup[]> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseWithGroup[]>(`${this.baseUrl}/current-courses-with-groups`, {params});
  }

  getOverdueCoursesWithGroup(fieldOfStudyCode: string): Observable<CourseWithGroup[]> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('fieldOfStudyCode', fieldOfStudyCode);
    return this.http.get<CourseWithGroup[]>(`${this.baseUrl}/overdue-courses-with-groups`, {params});
  }

  subscribeToTheGroup(subscription: Subscription): Observable<Message> {
    subscription.studentIndex = this.userService.getStudentIndex();
    return this.http.post<Message>(`${this.baseUrl}/subscription`, subscription);
  }

  unsubscribeFromTheGroup(groupCode: string): Observable<Message> {
    const params = new HttpParams()
      .set('studentIndex', this.userService.getStudentIndex())
      .set('groupCode', groupCode);
    return this.http.delete<Message>(`${this.baseUrl}/subscription`, {params});
  }
}
