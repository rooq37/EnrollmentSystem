import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {AuthService} from '@auth0/auth0-angular';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  studentIndex: string;
  email: string;
  studentIndexSubject: Subject<string> = new Subject();
  private readonly STUDENT_INDEX = '__STUDENT_INDEX__';
  isLogged: boolean;

  constructor(private authService: AuthService) {
    this.studentIndexSubject.subscribe(index => this.studentIndex = index);
  }

  setStudentIndex(studentIndex: string): void {
    localStorage.setItem(this.STUDENT_INDEX, studentIndex);
  }

  getStudentIndex(): string {
    return localStorage.getItem(this.STUDENT_INDEX);
  }

  login(): void {
    this.authService.loginWithRedirect();
  }

  logout(): void {
    this.authService.logout();
    localStorage.clear();
  }

  setUserData(): void {
    this.authService.user$.subscribe(
      user => {
        this.setStudentIndex(user.nickname);
        this.email = user.email;
      }
    );
  }

  setIsUserLoggedIn(): void {
    this.authService.isAuthenticated$.subscribe(
      res => {
        this.isLogged = res;
      }
    );
  }

}
