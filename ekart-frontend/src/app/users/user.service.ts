import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InformationService } from '../_services/information.service';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private informationService: InformationService) { }

  getUsers(): Observable<Array<User>> {
    return this.http.get<Array<User>>(this.informationService.allUsers);
  }
}
