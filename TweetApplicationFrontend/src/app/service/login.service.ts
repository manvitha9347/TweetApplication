import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { Login } from '../models/login.model';
import { UserDetailsResponse } from '../models/UserDetailsResponse.model';
import { UserEntity } from '../models/UserEntity.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loginIds: string[] = [];
  isLogin = new BehaviorSubject<boolean>(false);
  ROOT_URL:string = 'http://loginservice-env.eba-aain2mmm.ap-northeast-1.elasticbeanstalk.com';
  constructor(private http: HttpClient) { }

  userLogin(loginDetails: Login): Observable<string> {
    return this.http.post<string>(this.ROOT_URL+'/api/v1.0/tweets/login', loginDetails,{responseType:'string' as 'json'});
  }
  getAllUser():Observable<String[]> {
    return this.http.get<string[]>(this.ROOT_URL+'/api/v1.0/tweets/users').pipe(
      map((value)=>{
        this.loginIds=value;
        return this.loginIds
      })
    );
  }
  registerNewUser(userEntity:UserEntity){
    return this.http.post<string>(this.ROOT_URL+'/api/v1.0/tweets/register',userEntity,{responseType:'string' as 'json'} );
  }

  getAllDetailsOfUser(userName: string):Observable<UserDetailsResponse>{
    return this.http.get<UserDetailsResponse>(this.ROOT_URL+`/api/v1.0/tweets/userDetails/${userName}`);
  }

  get loginSubject(){
    return this.isLogin.asObservable();
  }

  isLoginClicked(){
    let userName = sessionStorage.getItem('userId');
    if(userName){
      this.isLogin.next(true);
    }
  }

  isLogoutClicked(){
    let userName = sessionStorage.getItem('userId');
    if(!userName){
      this.isLogin.next(false);
    }
  }

  forgotPassword(forgotPassword:any){
    return this.http.post<string>(this.ROOT_URL+`/api/v1.0/tweets/forgot/${forgotPassword.loginId}`,forgotPassword,{responseType:'string' as 'json'});
  }


}
