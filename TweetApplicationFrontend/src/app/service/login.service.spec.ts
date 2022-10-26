import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoginService } from './login.service';
import { HttpClient } from '@angular/common/http';
import { Login } from '../models/login.model';
import { BehaviorSubject, of } from 'rxjs';

describe('LoginService', () => {
  let service: LoginService;

  let http = jasmine.createSpyObj(HttpClient, ['post', 'get']);
  beforeEach(() => {
    TestBed.configureTestingModule({});
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [],
    });
    service = new LoginService(http);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return token', () => {
    const mockLogin: Login = {
      loginId: 'sandeep',
      password: '1314',
    };
    http.post.and.returnValue(of('abcd'));
    service.userLogin(mockLogin).subscribe({
      next: (data) => expect(data).toEqual('abcd'),
    });
  });
  it('should return all users', () => {
    const mockUsers = ['sandeep', 'manvitha', 'chetan'];
    http.get.and.returnValue(of(mockUsers));
    service.getAllUser().subscribe({
      next: (data) => expect(data).toEqual(mockUsers),
    });
  });
  it('should registerNewUser', () => {
    const mockUserEntity = {
      firstName: 'yandakuditi',
      lastName: 'sandeep',
      email: 'abc@email.com',
      loginId: 'yanda1314',
      password: '11314',
      confirmPassword: '1314',
      contactNumber: '99394002323',
    };
    http.post.and.returnValue(of('yanda1314'));
    service.registerNewUser(mockUserEntity).subscribe({
      next: (data) => expect(data).toEqual('yanda1314'),
    });
  });

  it('should call isLoginClicked', () => {
    let spyObj = spyOn(service, 'isLoginClicked');
    spyOn(sessionStorage,'getItem').and.returnValue('sandeep');
    service.isLoginClicked();
    expect(spyObj).toHaveBeenCalled();
  });

  it('should call isLogoutClicked', () => {
    let spyObj = spyOn(service, 'isLogoutClicked');
    service.isLogoutClicked();
    expect(spyObj).toHaveBeenCalled();
  });
  it('should return forgotPassword', () => {
    const mockData = {
      loginId: 'yanda1314',
      email: 'abc@email.com',
      password: 'abcd',
      confirmPassword: 'abcd',
    };
    http.post.and.returnValue(of('password changed succesfully'));
    service.forgotPassword(mockData).subscribe({
      next: (data) => expect(data).toEqual('password changed succesfully'),
    });
  });
});
