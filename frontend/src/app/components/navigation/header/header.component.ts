import {
  Component,
  OnInit,
} from '@angular/core';
import {AuthService} from '@auth0/auth0-angular';
import {UserService} from '../../../services/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  email: string;
  studentIndex: string;
  userService: UserService;

  constructor(userService: UserService) {
    this.userService = userService;
  }

  ngOnInit(): void {
    this.userService.setIsUserLoggedIn();
    this.userService.setUserData();
  }

}
