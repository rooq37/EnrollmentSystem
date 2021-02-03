import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserService} from "../services/user/user.service";
import {switchMap} from "rxjs/operators";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private userService: UserService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this.userService.getToken().pipe(
      switchMap(token => next.handle(request.clone({
      })))
    );
  }
}
