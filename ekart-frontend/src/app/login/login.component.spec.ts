import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginService } from './login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, NavigationExtras } from '@angular/router';
import { Login } from './Login';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService: LoginService;
  let router: Router;

  class LoginServiceStub {
    checkUser(x: Login): boolean {
      return true;
    };
  };

  class RouterStub {
    navigate(commands: any[], extras?: NavigationExtras) { };
  };

  let loginServiceStub: LoginServiceStub = new LoginServiceStub();
  let routerStub: RouterStub = new RouterStub();


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule, FormsModule],
      providers: [{ provide: LoginService, useValue: loginServiceStub },
      { provide: Router, useValue: routerStub }
      ],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    loginService = TestBed.get(LoginService);
    router = TestBed.get(Router);

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;

    //component.ngOnInit();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

}