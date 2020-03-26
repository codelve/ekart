import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from './register.service';
import { Subscription } from 'rxjs';
import { MustMatch } from '../utils/must-match.validator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {

  submit: boolean;
  successMsg: string;
  errorMsg: string;
  registerForm: FormGroup;
  account: string[] = ['admin', 'buyer', 'seller'];
  private subscription: Subscription;
  constructor(private formBuilder: FormBuilder, private router: Router,
              private registerService: RegisterService) {
  }

  register() {
    this.submit = true;
    this.subscription = this.registerService.register(this.registerForm.value).subscribe(
      data => {
        this.submit = true;
        this.successMsg = 'User registered successfully!';
        this.router.navigate(['/login']);
      },
      err => {
        if (err.status === 200) {
          this.submit = true;
          this.successMsg = 'User registered successfully!';
          this.router.navigate(['/login']);
        } else {
          this.errorMsg = err.error.message;
          this.submit = false;
        }
      }
    );
  }



  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6),
      Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{4,}')]],
      confirmPassword: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      roles: ['']
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
