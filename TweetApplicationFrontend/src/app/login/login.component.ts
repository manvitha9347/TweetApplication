import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { LoginService } from '../service/login.service';
import { loginIdValidator } from '../Validators/LoginIdValidator.validator';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginDetails: Login = { loginId: '', password: '' };
  token: string = '';
  errorMessage = '';
  isRegistrationClicked: boolean = false;
  newUserId$: Observable<string> | undefined;
  isLoginIdLoaded = false;
  showValidationError = false;
  loginIds: string[] = [];
  constructor(private loginService: LoginService, private route: Router) {}
  ngOnInit(): void {
    this.loginDetails.loginId = '';
    this.loginDetails.password = '';
  }
  registrationForm: FormGroup = new FormGroup({
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.required, Validators.email]),
    loginId: new FormControl(null, [
      Validators.required,
      loginIdValidator(this.loginService.loginIds),
    ]),
    password: new FormControl(null, [Validators.required]),
    confirmPassword: new FormControl(null, [Validators.required]),
    contactNumber: new FormControl(null, [Validators.required]),
  });
  checkPasswordMatching(password: string, confirmPassword: string): void {
    if (!!password && !!confirmPassword) {
      if (password === confirmPassword) {
        this.showValidationError = false;
      } else if (password !== confirmPassword) {
        this.showValidationError = true;
      }
    }
  }
  handelLogin() {
    this.loginService.userLogin(this.loginDetails).subscribe(
      (data) => {
        this.errorMessage = '';
        this.token = data;
        sessionStorage.setItem('token', this.token);
        sessionStorage.setItem('userId', this.loginDetails.loginId);
        this.loginService.isLoginClicked();
        this.route.navigate(['home']);
      },
      (err) => {
        this.errorMessage = err.error;
      }
    );
  }
  navigateToRegistration() {
    this.isRegistrationClicked = true;
  }
  navigateToLogin() {
    this.isRegistrationClicked = false;
  }
  handelRegistration() {
    if (!this.showValidationError) {
      this.loginService
        .registerNewUser(this.registrationForm?.value)
        .subscribe((data: string) => {
          this.loginDetails.loginId = data;
          this.registrationForm.reset();
        });
      setTimeout(() => (this.isRegistrationClicked = false), 1000);
    }
  }
}
