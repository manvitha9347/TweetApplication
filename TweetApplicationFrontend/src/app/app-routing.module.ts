import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { LoginComponent } from './login/login.component';
import { UserLoginRouteGaurd } from './RouteGaurds/UserLoginRouteGaurd';
import { TweetComponent } from './tweet/tweet.component';
import { ViewAllUsersComponent } from './view-all-users/view-all-users.component';
import { ViewUserTweetsComponent } from './view-user-tweets/view-user-tweets.component';

const routes: Routes = [
  {component:LoginComponent,path:'login'},
  {component:ViewUserTweetsComponent,path:'tweets',canActivate:[UserLoginRouteGaurd]},
  {component:ViewAllUsersComponent,path:'allusers',canActivate:[UserLoginRouteGaurd]},
  {component:TweetComponent,path:'home',canActivate:[UserLoginRouteGaurd]},
  {component:TweetComponent,path:'',canActivate:[UserLoginRouteGaurd]},
  {component:ForgetPasswordComponent,path:'forgetpassword'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
