import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const redirectUrl = route.data.AuthGuardRedirect;
    if (this.tokenStorage.getUser()) {
      this.tokenStorage.showLogoutButton();
      return true;
    } else {
      if (redirectUrl !== null && redirectUrl !== undefined) {
        this.router.navigate(['login', redirectUrl]);
      } else {
        this.router.navigate(['login']);
      }
    }
  }
}
