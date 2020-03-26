import { Injectable } from '@angular/core';

@Injectable()
export class InformationService {

  email: string;
  loginUrl = 'http://localhost:8200/auth/login';
  registerUrl = 'http://localhost:8200/auth/signup/';
  productUrl = `http://localhost:8200/products/`;
  moblieProductUrl = `http://localhost:8200/products/mobiles`;
  tabletProductUrl = `http://localhost:8200/products/tablets`;
  profileUrl = `http://localhost:8200/profile/`;
  allUsers = `http://localhost:8200/allUsers/`;
}
