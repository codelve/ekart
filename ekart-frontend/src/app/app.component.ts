import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from './_services/token-storage.service';
import { Subject } from 'rxjs';

@Component({
    selector: 'app-root',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.css']
})
export class AppComponent implements OnInit {
    pageTitle = 'Infy Store';
    private roles: string[];
    isLoggedIn = false;
    showAdminDashboard = false;
    showSellerDashboard = false;
    username: string;
    constructor(private router: Router, private tokenStorageService: TokenStorageService) { }

    showLogout: Subject<boolean> = this.tokenStorageService.showLogout;


    ngOnInit(): void {
        this.tokenStorageService.getUsername().subscribe(resp => {
            if (resp) {
                this.username = resp;
            }
        });

        this.tokenStorageService.getUserRoles().subscribe(response => {
            this.showAdminDashboard = false;
            this.showSellerDashboard = false;
            if (response) {
                if (response.includes('ROLE_ADMIN')) {
                    this.showAdminDashboard = true;
                }
                if (response.includes('ROLE_SELLER')) {
                    this.showSellerDashboard = true;
                }
            }
        });
    }

    logout() {
        this.tokenStorageService.signOut();
        this.router.navigate(['/']);
    }
}
