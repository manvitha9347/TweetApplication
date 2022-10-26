import { Component, OnInit } from '@angular/core';
import { UserDetailsResponse } from '../models/UserDetailsResponse.model';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  constructor(private loginService: LoginService) {}
  userResponse?: UserDetailsResponse;

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails() {
    let userName = sessionStorage.getItem('userId')!;
    this.loginService.getAllDetailsOfUser(userName).subscribe((response) => {
      this.userResponse = response;
    });
  }
}
