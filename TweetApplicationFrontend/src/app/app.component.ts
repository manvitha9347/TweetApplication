import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  isLoginClicked: boolean = true;
  title = 'TweetApplication';
  wait = false;
  constructor(private loginService: LoginService) {}
  ngOnInit(): void {
    this.loginService.isLoginClicked();
    setTimeout(() => (this.wait = true), 1000);
  }

  exsistingLoginIds$ = this.loginService.getAllUser();

  login$ = this.loginService.loginSubject;
}
