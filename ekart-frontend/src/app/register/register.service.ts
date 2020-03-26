import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InformationService } from '../_services/information.service';
import { Register } from './register';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class RegisterService {

  constructor(private informationService: InformationService, private http: HttpClient) { }

  register(data: Register): Observable<any> {
    return this.http.post(this.informationService.registerUrl, data, httpOptions);
  }
}
