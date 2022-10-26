import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";

@Injectable()
export class UserLoginRouteGaurd implements CanActivate {
  constructor(private router: Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    let userName = sessionStorage.getItem('userId');
    let token = sessionStorage.getItem('token');
    if (userName === null && token === null) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

}
