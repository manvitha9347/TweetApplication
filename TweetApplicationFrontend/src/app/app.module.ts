import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TweetComponent } from './tweet/tweet.component';
import { MenuComponent } from './menu/menu.component';
import { LoginComponent } from './login/login.component';
import { CreateTweetComponent } from './create-tweet/create-tweet.component';
import { ViewTweetsComponent } from './view-tweets/view-tweets.component';
import { ViewAllUsersComponent } from './view-all-users/view-all-users.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';
import { JwtInterceptor } from './JwtInterceptor.interceptor';
import {MatCardModule} from '@angular/material/card';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatMenuModule} from '@angular/material/menu';
import {MatDividerModule} from '@angular/material/divider';
import { UserLoginRouteGaurd } from './RouteGaurds/UserLoginRouteGaurd';
import { TweetLikesComponent } from './tweet-likes/tweet-likes.component';
import { TweetCommentsComponent } from './tweet-comments/tweet-comments.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { ViewUserTweetsComponent } from './view-user-tweets/view-user-tweets.component';

@NgModule({
  declarations: [
    AppComponent,
    TweetComponent,
    MenuComponent,
    LoginComponent,
    CreateTweetComponent,
    ViewTweetsComponent,
    ViewAllUsersComponent,
    TweetLikesComponent,
    TweetCommentsComponent,
    UserProfileComponent,
    ForgetPasswordComponent,
    ViewUserTweetsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    MatExpansionModule,
    MatDividerModule
  ],
  providers: [
    UserLoginRouteGaurd,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
