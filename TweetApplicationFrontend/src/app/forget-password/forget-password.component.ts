import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css'],
})
export class ForgetPasswordComponent implements OnInit {
  constructor(private loginService: LoginService, private router: Router) {}
  showBackButton = false;
  showChangePasswordButton = true;
  successMessage: string = '';
  errorMessage: string = '';
  showValidationError = false;
  ngOnInit(): void {}

  checkPasswordMatching(password: string, confirmPassword: string): void {
    if (!!password && !!confirmPassword) {
      if (password === confirmPassword) {
        this.showValidationError = false;
      } else if (password !== confirmPassword) {
        this.showValidationError = true;
      }
    }
  }

  handelForgotPassword() {
    if (!this.showValidationError) {
      this.loginService.forgotPassword(this.forgotPasswordForm.value).subscribe(
        (data: string) => {
          this.successMessage = data;
          this.errorMessage = '';
          this.showBackButton = true;
          this.showChangePasswordButton = false;
        },
        (err: HttpErrorResponse) => {
          this.successMessage ='';
          this.errorMessage = err.error;
        }
      );
    }
  }

  forgotPasswordForm: FormGroup = new FormGroup({
    loginId: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
  });

  backToLoginPage() {
    this.router.navigate(['login']);
  }
}
