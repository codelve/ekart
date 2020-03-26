import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InformationService } from '../_services/information.service';
import { Profile } from './profile';


@Injectable()
export class ProfileService {

    constructor(private informationService: InformationService, private http: HttpClient) { }

    getProfile(): Observable<Profile> {
        return this.http.get<Profile>(this.informationService.profileUrl);
    }
}
