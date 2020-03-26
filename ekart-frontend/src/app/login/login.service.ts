import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InformationService } from '../_services/information.service';
import { Login } from './login';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class LoginService {
    email: string;
    constructor(private http: HttpClient, private informationService: InformationService) { }


    login(data: Login): Observable<any> {
        return this.http.post(this.informationService.loginUrl, data, httpOptions);
    }

}
