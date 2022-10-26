import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  isLoginClicked=true;
  constructor(public loginService:LoginService) { }

  ngOnInit(): void {
  }

 logoutClicked(){
   sessionStorage.removeItem('userId');
   sessionStorage.removeItem('token');
    this.loginService.isLogoutClicked();

  }
}
