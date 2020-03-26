import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatIconModule, MatListModule, MatPaginatorModule,
    MatSidenavModule, MatSortModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { ProfileService } from './profile/profile.service';
import { RegisterComponent } from './register/register.component';
import { RegisterService } from './register/register.service';
import { UsersComponent } from './users/users.component';
import { AuthInterceptor } from './utils/auth.interceptor';
import { WelcomeComponent } from './welcome/welcome.component';
import { AuthGuardService } from './_services/auth-guard.service';
import { InformationService } from './_services/information.service';




@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        MatSidenavModule,
        MatButtonModule,
        MatToolbarModule,
        MatIconModule,
        MatListModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule
    ],
    declarations: [AppComponent, WelcomeComponent, LoginComponent, RegisterComponent, ProfileComponent, UsersComponent],
    providers: [InformationService, RegisterService, ProfileService, AuthGuardService,
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
