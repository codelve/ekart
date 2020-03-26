import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Login } from './Login';
import { LoginService } from './login.service';
import { Subscription } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit, OnDestroy {

  login = new Login();

  users: any[];
  roles: string[] = [];
  valid = true;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  redirectUrl: string = null;
  private subscription: Subscription;

  constructor(private route: ActivatedRoute, private router: Router,
              private loginService: LoginService, private tokenStorage: TokenStorageService) {

  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.redirectUrl = params.redirectUrl;
    });
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit() {
    this.valid = true;
    this.subscription = this.loginService.login(this.login).subscribe(
      data => {
        const redirect = (this.redirectUrl !== null && this.redirectUrl !== undefined) ? this.redirectUrl : 'products';
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.router.navigate([redirect]);
      },
      err => {
        this.errorMessage = err;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
