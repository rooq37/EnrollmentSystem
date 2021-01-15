import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {EnrollmentBlocks} from '../../models/enrollment/enrollment-blocks';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {

  baseUrl = environment.urlAddress + '/api/enrollment/';

  constructor(private http: HttpClient) { }

  getEnrollmentBlocks(studentIndex: string): Observable<EnrollmentBlocks> {
    return this.http.get<EnrollmentBlocks>(`${this.baseUrl}/blocks/${studentIndex}`, httpOptions);
  }
}
