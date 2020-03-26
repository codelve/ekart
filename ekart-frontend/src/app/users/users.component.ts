import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from './user.service';
import { User } from './user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, OnDestroy {

  public users: User[];
  private subscription: Subscription;
  errorMessage: string;

  displayedColumns: string[] = ['name', 'email'];
  dataSource = this.users;

  constructor(private service: UserService) { }

  ngOnInit(): void {
    this.subscription = this.service.getUsers().subscribe(
      items => {
          this.users = items;
      },
      error => this.errorMessage = error);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
