import { Injectable, OnInit } from '@angular/core';
import { Subject, BehaviorSubject, Observable } from 'rxjs';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  showLogout = new Subject<boolean>();
  public userRoles = new BehaviorSubject<Array<string>>(undefined);
  public userName = new BehaviorSubject<string>(undefined);

  constructor() { }

  signOut() {
    this.hideLogoutButton();
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  showLogoutButton() {
    this.showLogout.next(true);
  }

  hideLogoutButton() {
    this.showLogout.next(false);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user) {
    this.showLogoutButton();
    window.sessionStorage.removeItem(USER_KEY);
    this.setUserRoles(user.roles);
    this.userName.next(user.username);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  setUserRoles(roles: Array<string>) {
    this.userRoles.next(roles);
  }

  getUserRoles(): Observable<Array<string>> {
    return this.userRoles.asObservable();
  }

  getUsername(): Observable<string> {
    return this.userName.asObservable();
  }

  public getUser() {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }
}
