import { Component, OnInit, OnDestroy } from '@angular/core';

import { Profile } from './profile';
import { ProfileService } from './profile.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  profileDetail: Profile;
  errorMsg: string;
  personalInfo = true;
  private subscription: Subscription;
  constructor(private profileService: ProfileService) { }

  getProfileDetails() {
    this.subscription = this.profileService.getProfile().subscribe(
      data => {
        this.profileDetail = data;
      },
      error => this.errorMsg = error
    );
  }

  ngOnInit() {
    this.getProfileDetails();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
